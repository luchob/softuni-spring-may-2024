package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.UserLoginDTO;
import bg.softuni.mobilele.model.UserRegistrationDTO;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.enums.UserRoleEnum;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.repository.UserRoleRepository;
import bg.softuni.mobilele.service.UserService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final ModelMapper modelMapper;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;

  public UserServiceImpl(ModelMapper modelMapper,
      PasswordEncoder passwordEncoder,
      UserRepository userRepository,
      UserRoleRepository userRoleRepository) {
    this.modelMapper = modelMapper;
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.userRoleRepository = userRoleRepository;
  }

  @Override
  public void registerUser(UserRegistrationDTO userRegistration) {

    UserEntity newUser = map(userRegistration);

    newUser.setRoles(List.of(userRoleRepository.findByRole(UserRoleEnum.USER).orElseThrow()));

    userRepository.save(newUser);
  }

  private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
    UserEntity mappedEntity = modelMapper.map(userRegistrationDTO, UserEntity.class);

    mappedEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

    return mappedEntity;
  }
}
