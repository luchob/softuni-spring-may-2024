package bg.softuni.mobilele.service.impl;

import static org.mockito.Mockito.when;

import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.enums.UserRoleEnum;
import bg.softuni.mobilele.model.user.MobileleUserDetails;
import bg.softuni.mobilele.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith(MockitoExtension.class)
class MobileleUserDetailsServiceTest {

  private static final String TEST_EMAIL = "user@example.com";
  private static final String NOT_EXISTENT_EMAIL = "noone@example.com";

  private MobileleUserDetailsService toTest;
  @Mock
  private UserRepository mockUserRepository;

  @BeforeEach
  void setUp() {
    toTest = new MobileleUserDetailsService(mockUserRepository);
  }

  @Test
  void testLoadUserByUsername_UserFound() {

    // Arrange
    UserEntity testUser = new UserEntity()
        .setEmail(TEST_EMAIL)
        .setPassword("topsecret")
        .setFirstName("Pesho")
        .setLastName("Petrov")
        .setRoles(List.of(
            new UserRoleEntity().setRole(UserRoleEnum.ADMIN),
            new UserRoleEntity().setRole(UserRoleEnum.USER)
        ));

    when(mockUserRepository.findByEmail(TEST_EMAIL))
        .thenReturn(Optional.of(testUser));

    // Act
    UserDetails userDetails = toTest.loadUserByUsername(TEST_EMAIL);

    // Assert
    Assertions.assertInstanceOf(MobileleUserDetails.class, userDetails);

    MobileleUserDetails mobileleUserDetails = (MobileleUserDetails) userDetails;

    Assertions.assertEquals(TEST_EMAIL, userDetails.getUsername());
    Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
    Assertions.assertEquals(testUser.getFirstName(), mobileleUserDetails.getFirstName());
    Assertions.assertEquals(testUser.getLastName(), mobileleUserDetails.getLastName());
    Assertions.assertEquals(testUser.getFirstName() + " " + testUser.getLastName(),
        mobileleUserDetails.getFullName());

    var expectedRoles = testUser.getRoles().stream().map(UserRoleEntity::getRole).map(r -> "ROLE_" + r).toList();
    var actualRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

    Assertions.assertEquals(expectedRoles, actualRoles);
  }

  @Test
  void testLoadUserByUsername_UserNotFound() {
    Assertions.assertThrows(
        UsernameNotFoundException.class,
        () -> toTest.loadUserByUsername(NOT_EXISTENT_EMAIL)
    );
  }

}
