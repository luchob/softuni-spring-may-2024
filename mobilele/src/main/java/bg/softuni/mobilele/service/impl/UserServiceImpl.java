package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.MobileleApplication;
import bg.softuni.mobilele.model.dto.UserLoginDTO;
import bg.softuni.mobilele.model.dto.UserRegistrationDTO;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.user.MobileleUserDetails;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.service.UserService;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final ModelMapper modelMapper;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public UserServiceImpl(ModelMapper modelMapper,
      PasswordEncoder passwordEncoder,
      UserRepository userRepository) {
    this.modelMapper = modelMapper;
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  @Override
  public void registerUser(UserRegistrationDTO userRegistration) {
    userRepository.save(map(userRegistration));
  }

  public Optional<MobileleUserDetails> getCurrentUser() {
    Authentication authentication = SecurityContextHolder
        .getContextHolderStrategy()
        .getContext()
        .getAuthentication();

    if (authentication != null &&
        authentication.isAuthenticated() &&
        authentication.getPrincipal() instanceof MobileleUserDetails ud) {

      return Optional.of(ud);
    }

    return Optional.empty();
  }

  private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
    UserEntity mappedEntity = modelMapper.map(userRegistrationDTO, UserEntity.class);

    mappedEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

    return mappedEntity;
  }
}
