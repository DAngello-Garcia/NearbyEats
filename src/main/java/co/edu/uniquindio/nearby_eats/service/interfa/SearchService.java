package co.edu.uniquindio.nearby_eats.service.interfa;

import co.edu.uniquindio.nearby_eats.dto.SaveSearchDTO;
import co.edu.uniquindio.nearby_eats.dto.response.place.PlaceResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.place.RecommendPlacesException;
import co.edu.uniquindio.nearby_eats.exceptions.user.GetUserException;

import java.util.List;

public interface SearchService {

    void saveSearch(SaveSearchDTO saveSearchDTO);

    List<PlaceResponseDTO> recommendPlaces(String userId) throws RecommendPlacesException, GetUserException;
}
