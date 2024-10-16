package co.edu.uniquindio.nearby_eats.test;

import co.edu.uniquindio.nearby_eats.dto.email.EmailDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.*;
import co.edu.uniquindio.nearby_eats.dto.response.TokenDTO;
import co.edu.uniquindio.nearby_eats.dto.response.user.UserInformationDTO;
import co.edu.uniquindio.nearby_eats.exceptions.authentication.AuthtenticationException;
import co.edu.uniquindio.nearby_eats.exceptions.email.EmailServiceException;
import co.edu.uniquindio.nearby_eats.exceptions.user.ChangePasswordException;
import co.edu.uniquindio.nearby_eats.exceptions.user.GetAllUserException;
import co.edu.uniquindio.nearby_eats.model.docs.User;
import co.edu.uniquindio.nearby_eats.model.enums.UserRole;
import co.edu.uniquindio.nearby_eats.repository.UserRepository;
import co.edu.uniquindio.nearby_eats.service.interfa.AuthenticationService;
import co.edu.uniquindio.nearby_eats.service.interfa.EmailService;
import co.edu.uniquindio.nearby_eats.service.interfa.UserService;
import co.edu.uniquindio.nearby_eats.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtUtils jwtUtils;

    private final String userId = "client2";

    @Test
    public void loginUser() throws AuthtenticationException {
        UserLoginDTO userLoginDTO = new UserLoginDTO(
                "juanito@gmail.com",
                "123456"
        );
        TokenDTO tokenDTO = authenticationService.loginUser(userLoginDTO);

        Assertions.assertNotNull(tokenDTO);
    }

    @Test
    public void loginModerator() throws AuthtenticationException {
        ModeratorLoginDTO moderatorLoginDTO = new ModeratorLoginDTO(
                "atomosMod@correo.com",
                "juandis"
        );
        TokenDTO tokenDTO = authenticationService.loginModerator(moderatorLoginDTO);

        Assertions.assertNotNull(tokenDTO);
    }

    @Test
    public void registerUserTest() throws Exception {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(
                "Juan",
                "Perez",
                "juanito@gmail.com",
                "123456",
                "juanito123",
                "Armenia",
                "Imagen de perfil",
                "123456"
        );

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(userRegistrationDTO.password());
        User user = User.builder()
                .id("client2")
                .nickname(userRegistrationDTO.nickname())
                .city(userRegistrationDTO.city())
                .profilePicture(userRegistrationDTO.profilePicture())
                .firstName(userRegistrationDTO.firstName())
                .lastName(userRegistrationDTO.lastName())
                .password(encryptedPassword)
                .email(userRegistrationDTO.email())
                .isActive(true)
                .role(UserRole.CLIENT.name())
                .build();

        User user1 = userRepository.save(user);
        Assertions.assertNotNull(user1);
    }

    @Test
    public void registerTwoUserTest() {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(
                "Pedrito",
                "Pérez",
                "pedri@correo.com",
                "pedir",
                "yose.jpg",
                "Montenegro",
                "elpepe",
                "123456"
        );

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(userRegistrationDTO.password());
        User user = User.builder()
                .id("client3")
                .nickname(userRegistrationDTO.nickname())
                .city(userRegistrationDTO.city())
                .profilePicture(userRegistrationDTO.profilePicture())
                .firstName(userRegistrationDTO.firstName())
                .lastName(userRegistrationDTO.lastName())
                .password(encryptedPassword)
                .email(userRegistrationDTO.email())
                .isActive(true)
                .role(UserRole.CLIENT.name())
                .build();

        User user1 = userRepository.save(user);
        Assertions.assertNotNull(user1);
    }

    @Test
    public void updateUserTest() throws Exception {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(
                "client2",
                "Perez",
                "jloaizanieto@gmail.com",
                "Armenia",
                "Imagen de perfil 2"
        );

        Optional<User> clientOptional = userRepository.findByEmail(userUpdateDTO.email());

        User user = clientOptional.get();
        user.setFirstName(userUpdateDTO.firstName());
        user.setLastName(userUpdateDTO.lastName());
        user.setEmail(userUpdateDTO.email());
        user.setCity(userUpdateDTO.city());
        user.setProfilePicture(userUpdateDTO.profilePicture());
        userRepository.save(user);

        Assertions.assertNotNull(user);
    }

    @Test
    public void deleteUserTest() {
        Assertions.assertDoesNotThrow(() -> userService.deleteUser(userId));
    }

    @Test
    public void getAllUsersTest() throws GetAllUserException {
        int expectedUsers = 1;
        List<UserInformationDTO> users = userService.getAllUsers();
        Assertions.assertEquals(expectedUsers, users.size());
    }

    @Test
    public void getUserTest() throws Exception {
        UserInformationDTO user = userService.getUser(userId);
        Assertions.assertNotNull(user);
    }

    @Test
    public void sendRecoveryEmailTest() throws MessagingException, EmailServiceException {
        String email = "dangelloa.garcian@uqvirtual.edu.co";

        Optional<User> clientOptional = userRepository.findByEmail(email);

        emailService.sendEmail(new EmailDTO("Cambio de contraseña de NearbyEats",
                "Para cambiar la contraseña ingrese al siguiente enlace http://......./params ", email));

        Assertions.assertNotNull(clientOptional);
    }

    @Test
    public void changePasswordTest() throws ChangePasswordException {
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO(
                "juanda29",
                "$2a$10$Se1GLM8hfjywo69nPVtkhekiHzUbU6uAqqQhe8zk25RLZoLKzaGxW"
        );
        userService.changePassword(userChangePasswordDTO);
    }

}
