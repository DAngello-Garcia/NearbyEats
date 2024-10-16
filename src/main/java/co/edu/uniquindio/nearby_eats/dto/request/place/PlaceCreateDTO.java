package co.edu.uniquindio.nearby_eats.dto.request.place;

import co.edu.uniquindio.nearby_eats.model.enums.PlaceCategory;
import co.edu.uniquindio.nearby_eats.model.subdocs.Location;
import co.edu.uniquindio.nearby_eats.model.subdocs.Schedule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record PlaceCreateDTO(
        @NotBlank(message = "name is required" ) String name,
        @NotBlank(message = "description is required" ) String description,
        Location location,
        @NotBlank(message = "images is empty") List<String> pictures,
        @NotBlank(message = "schedule is empty" ) List<Schedule> schedule,
        @NotBlank(message = "phones is empty" ) List<String> phones,
        @NotBlank(message = "No category has been established" ) List<String> categories,
        @NotBlank(message = "clientId is required") String clientId
) {
}
