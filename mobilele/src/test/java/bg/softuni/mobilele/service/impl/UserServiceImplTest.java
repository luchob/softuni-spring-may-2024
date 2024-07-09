package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.UserRegistrationDTO;
import bg.softuni.mobilele.repository.UserRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

@Disabled
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

  private UserServiceImpl toTest;

  @Mock
  private UserRepository mockUserRepository;

  @Mock
  private PasswordEncoder mockPasswordEncoder;

  @BeforeEach
  void setUp() {

    toTest = new UserServiceImpl(
        new ModelMapper(),
        mockPasswordEncoder,
        mockUserRepository
    );

  }

  @Test
  void testUserRegistration() {
    // Arrange
    UserRegistrationDTO userRegistrationDTO =
        new UserRegistrationDTO()
            .setFirstName("Anna")
            .setLastName("Dimitrova")
            .setPassword("topsecret")
            .setEmail("anna@example.com");

    // ACT
    toTest.registerUser(userRegistrationDTO);

    // TODO
  }

}
