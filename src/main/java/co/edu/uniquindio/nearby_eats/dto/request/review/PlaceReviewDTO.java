package co.edu.uniquindio.nearby_eats.dto.request.review;

import jakarta.validation.constraints.NotBlank;

public record PlaceReviewDTO(
        @NotBlank(message = "action is required") String action,
        @NotBlank(message = "comment is required") String comment,
        @NotBlank(message = "placeId is required") String placeId
) {
}
