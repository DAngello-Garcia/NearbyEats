package co.edu.uniquindio.nearby_eats.service.impl;

import co.edu.uniquindio.nearby_eats.dto.SaveSearchDTO;
import co.edu.uniquindio.nearby_eats.dto.response.place.PlaceResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.place.RecommendPlacesException;
import co.edu.uniquindio.nearby_eats.exceptions.user.GetUserException;
import co.edu.uniquindio.nearby_eats.model.docs.Place;
import co.edu.uniquindio.nearby_eats.model.docs.Search;
import co.edu.uniquindio.nearby_eats.model.enums.PlaceCategory;
import co.edu.uniquindio.nearby_eats.repository.PlaceRepository;
import co.edu.uniquindio.nearby_eats.repository.SearchRepository;
import co.edu.uniquindio.nearby_eats.repository.UserRepository;
import co.edu.uniquindio.nearby_eats.service.interfa.SearchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

    private final SearchRepository searchRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;


    public SearchServiceImpl(SearchRepository searchRepository, PlaceRepository placeRepository, UserRepository userRepository) {
        this.searchRepository = searchRepository;
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveSearch(SaveSearchDTO saveSearchDTO) {
        
        Search search = Search.builder()
                .user(saveSearchDTO.clientId())
                .date(saveSearchDTO.date())
                .query(saveSearchDTO.query())
                .build();

        searchRepository.save(search);
    }

    @Override
    public List<PlaceResponseDTO> recommendPlaces(String userId) throws RecommendPlacesException, GetUserException {

        if (! userRepository.existsById(userId)) {
            throw new GetUserException("El usuario no existe");
        }

        List<Search> searches = searchRepository.findAllByUser(userId);

        if (searches.isEmpty()) {
            throw new RecommendPlacesException("El usuario no tiene búsquedas realizadas");
        }

        List<String> keywords = searches.stream()
                .map(Search::getQuery)
                .flatMap(query -> Stream.of(query.split("\\s+")))
                .toList();

        // Filtrar categorías y preparar búsqueda por nombre
        List<PlaceCategory> categoriesToSearch = keywords.stream()
                .map(this::mapToCategory)
                .filter(java.util.Objects::nonNull)
                .toList();

        List<Place> places = placeRepository.findAllByCategoriesContaining(categoriesToSearch);

        return places.stream()
                .map(this::mapToDTO)
                .toList();
    }

    private PlaceResponseDTO mapToDTO(Place place) {
        return new PlaceResponseDTO(
                place.getId(),
                place.getName(),
                place.getDescription(),
                place.getLocation(),
                place.getPictures(),
                place.getSchedules(),
                place.getPhones(),
                place.getCategories(),
                place.getReviews(),
                place.getCreatedBy(),
                place.getStatus(),
                place.getScore(),
                place.isOpen()
        );
    }

    private PlaceCategory mapToCategory(String keyword) {
        try {
            return PlaceCategory.valueOf(keyword.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null; // Retorna null si el keyword no es una categoría válida
        }
    }
}
