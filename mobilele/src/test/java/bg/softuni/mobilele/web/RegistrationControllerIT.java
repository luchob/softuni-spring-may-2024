package bg.softuni.mobilele.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  void testRegistration() throws Exception {

    mockMvc.perform(post("/users/register")
        .param("email", "anna@example.com")
        .param("firstName", "Anna")
        .param("lastName", "Petrova")
        .param("password", "topsecret")
            .with(csrf())
    ).andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/"));

    Optional<UserEntity> userEntityOpt = userRepository.findByEmail("anna@example.com");

    Assertions.assertTrue(userEntityOpt.isPresent());

    UserEntity userEntity = userEntityOpt.get();

    Assertions.assertEquals("Anna", userEntity.getFirstName());
    Assertions.assertEquals("Petrova", userEntity.getLastName());

    Assertions.assertTrue(passwordEncoder.matches("topsecret", userEntity.getPassword()));
  }
}
