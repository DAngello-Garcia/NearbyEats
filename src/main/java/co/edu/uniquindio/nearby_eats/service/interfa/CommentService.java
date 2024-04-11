package co.edu.uniquindio.nearby_eats.service.interfa;

import co.edu.uniquindio.nearby_eats.dto.request.comment.CommentDTO;
import co.edu.uniquindio.nearby_eats.dto.request.comment.DeleteCommentDTO;
import co.edu.uniquindio.nearby_eats.dto.request.comment.ReplyDTO;
import co.edu.uniquindio.nearby_eats.dto.response.comment.CommentResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.comment.*;
import co.edu.uniquindio.nearby_eats.exceptions.email.EmailServiceException;
import jakarta.mail.MessagingException;

import java.util.List;

public interface CommentService {

    void createComment(CommentDTO commentDTO) throws CreateCommentException, MessagingException, EmailServiceException;

    void answerComment(ReplyDTO replyDTO) throws AnswerCommentException, MessagingException, EmailServiceException;

    void deleteComment(DeleteCommentDTO deleteCommentDTO) throws DeleteCommentException;

    List<CommentResponseDTO> getCommentsByPlace(String placeId) throws ListCommentsException;

    Float getAverageScoreByPlace(String placeId) throws GetAverageScoreCommentException;

}
