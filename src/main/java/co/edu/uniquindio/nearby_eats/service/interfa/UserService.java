package co.edu.uniquindio.nearby_eats.service.interfa;

import co.edu.uniquindio.nearby_eats.dto.request.user.UserChangePasswordDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserRegistrationDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserUpdateDTO;
import co.edu.uniquindio.nearby_eats.dto.response.user.UserInformationDTO;
import co.edu.uniquindio.nearby_eats.exceptions.email.EmailServiceException;
import co.edu.uniquindio.nearby_eats.exceptions.user.*;
import jakarta.mail.MessagingException;

import java.util.List;

public interface UserService {

    void register(UserRegistrationDTO userRegistrationDTO) throws UserRegistrationException;

    void updateUser(UserUpdateDTO userUpdateDTO, String token) throws UpdateAccountException;

    void deleteUser(String token) throws DeleteAccountException;

    void sendRecoveryEmail(String email) throws SendRecoveryEmailException, MessagingException, EmailServiceException;

    List<UserInformationDTO> getAllUsers() throws GetAllUserException;

    UserInformationDTO getUser(String id) throws GetUserException;

    void changePassword(UserChangePasswordDTO userChangePasswordDTO) throws ChangePasswordException;

}
