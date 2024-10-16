package co.edu.uniquindio.nearby_eats.service.interfa;

import co.edu.uniquindio.nearby_eats.dto.request.place.*;
import co.edu.uniquindio.nearby_eats.dto.request.review.PlaceReviewDTO;
import co.edu.uniquindio.nearby_eats.dto.response.place.PlaceResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.email.EmailServiceException;
import co.edu.uniquindio.nearby_eats.exceptions.place.*;
import co.edu.uniquindio.nearby_eats.exceptions.review.ReviewPlaceException;
import jakarta.mail.MessagingException;

import java.util.List;

public interface PlaceService {

    PlaceResponseDTO createPlace(PlaceCreateDTO placeCreateDTO) throws CreatePlaceException;

    PlaceResponseDTO updatePlace(UpdatePlaceDTO updatePlaceDTO) throws UpdatePlaceException;

    PlaceResponseDTO deletePlace(DeletePlaceDTO deletePlaceDTO) throws DeletePlaceException;

    PlaceResponseDTO getPlace(String placeId) throws GetPlaceException;

    List<PlaceResponseDTO> getPlacesByCategory(GetPlacesByCategoryDTO getPlacesByCategoryDTO, String token) throws GetPlaceException, SaveSearchException;

    List<PlaceResponseDTO> getPlacesByStatus(GetPlacesByStatusByClientDTO getPlacesByStatusByClientDTO, String token) throws GetPlaceException, SaveSearchException;

    List<PlaceResponseDTO> getPlacesMod(String status);

    List<PlaceResponseDTO> getPlacesByClientId(String clientId) throws GetPlaceException;

    PlaceResponseDTO getFavoritePlace(String placeId) throws GetPlaceException;

    List<PlaceResponseDTO> getPlacesByLocation(GetPlacesByLocation getPlacesByLocation, String token) throws GetPlaceException, SaveSearchException;

    // TODO: Implementar el método para obtener los lugares más cercanos a una ubicación dada y un radio de búsqueda

    List<PlaceResponseDTO> getPlacesByModerator(String status, String token) throws GetPlaceException;

    List<PlaceResponseDTO> getPlacesByName(GetPlacesByNameDTO getPlacesByNameDTO, String token);

    List<PlaceResponseDTO> getPlacesByNamePublic(GetPlacesByNameDTO getPlacesByNameDTO);

    PlaceResponseDTO saveFavoritePlace(String placeId, String token) throws FavoritePlaceException;

    PlaceResponseDTO deleteFavoritePlace(String placeId, String token) throws FavoritePlaceException;

    void reviewPlace(PlaceReviewDTO placeReviewDTO, String token) throws ReviewPlaceException, MessagingException, EmailServiceException;

    List<String> getPlaceStatus();

    boolean isOpen(String id) throws GetPlaceException;
}
