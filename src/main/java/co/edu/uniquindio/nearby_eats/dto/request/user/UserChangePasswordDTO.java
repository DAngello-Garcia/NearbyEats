package co.edu.uniquindio.nearby_eats.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserChangePasswordDTO(
        @NotBlank(message = "newPassword is required to change the password") String newPassword,
        @NotBlank(message = "recoveryToken is required to change the password") String recoveryToken
) {
}
