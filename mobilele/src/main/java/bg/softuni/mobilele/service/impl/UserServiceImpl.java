package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.UserLoginDTO;
import bg.softuni.mobilele.model.UserRegistrationDTO;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.service.CurrentUser;
import bg.softuni.mobilele.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final CurrentUser currentUser;

  public UserServiceImpl(UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      CurrentUser currentUser) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.currentUser = currentUser;
  }

  @Override
  public void registerUser(UserRegistrationDTO userRegistration) {
    userRepository.save(userEntity(userRegistration));
  }

  @Override
  public boolean login(UserLoginDTO userLoginDTO) {
    UserEntity user = userRepository.findByEmail(userLoginDTO.getEmail()).orElse(null);
    if (user == null) {
      return false;
    }

    if (passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
      currentUser.setLogged(true)
          .setFirstName(user.getFirstName())
          .setLastName(user.getLastName());
      return true;
    }

    return false;
  }

  private UserEntity userEntity(UserRegistrationDTO userRegistration) {
    return new UserEntity()
        .setActive(true)
        .setEmail(userRegistration.email())
        .setFirstName(userRegistration.firstName())
        .setLastName(userRegistration.lastName())
        .setPassword(passwordEncoder.encode(userRegistration.password()));
  }
}
