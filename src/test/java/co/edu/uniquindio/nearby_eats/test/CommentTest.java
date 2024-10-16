package co.edu.uniquindio.nearby_eats.test;

import co.edu.uniquindio.nearby_eats.dto.request.comment.CommentDTO;
import co.edu.uniquindio.nearby_eats.dto.request.comment.DeleteCommentDTO;
import co.edu.uniquindio.nearby_eats.dto.request.comment.ReplyDTO;
import co.edu.uniquindio.nearby_eats.dto.response.comment.CommentResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.comment.*;
import co.edu.uniquindio.nearby_eats.exceptions.email.EmailServiceException;
import co.edu.uniquindio.nearby_eats.model.docs.Comment;
import co.edu.uniquindio.nearby_eats.repository.CommentRepository;
import co.edu.uniquindio.nearby_eats.repository.PlaceRepository;
import co.edu.uniquindio.nearby_eats.service.interfa.CommentService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CommentTest {
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;
    private final String placeId = "place1";
    private final String userId = "client2";

    @Test
    public void createCommentTest() throws CreateCommentException, MessagingException, EmailServiceException, GetAverageScoreCommentException {
        CommentDTO commentDTO = new CommentDTO(placeId, "Excelente lugar", 5);
        String comment = commentService.createComment(commentDTO, userId);
        Assertions.assertNotNull(comment);
    }

    @Test
    public void answerCommentTest() throws AnswerCommentException, MessagingException, EmailServiceException {
        ReplyDTO replyDTO = new ReplyDTO( "respuesta", "comment1");
        String comment = commentService.answerComment(replyDTO, userId);
        Assertions.assertNotNull(comment);
    }

    @Test
    public void deleteCommentTest() throws DeleteCommentException {
        DeleteCommentDTO deleteCommentDTO = new DeleteCommentDTO("comment1");
        String comment = commentService.deleteComment(deleteCommentDTO, userId);
        Assertions.assertNotNull(comment);
    }

    @Test
    public void getCommentsByPlaceTest() throws ListCommentsException {
        List<CommentResponseDTO> places = commentService.getCommentsByPlace(placeId);
        Assertions.assertNotNull(places);
    }

    @Test
    public void getAverageScoreByPlaceTest() throws GetAverageScoreCommentException {
        Float score = commentService.getAverageScoreByPlace(placeId);
        Assertions.assertEquals(5f, score);
    }
}
