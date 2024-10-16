package co.edu.uniquindio.nearby_eats.dto.request.comment;

import jakarta.validation.constraints.NotBlank;

public record ReplyDTO (
        @NotBlank(message = "text reply") String text,
        @NotBlank(message = "commentId is required")  String commentId
){
}
