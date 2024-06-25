package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.UserLoginDTO;
import bg.softuni.mobilele.model.dto.UserRegistrationDTO;

public interface UserService {

  void registerUser(UserRegistrationDTO userRegistration);

  boolean login(UserLoginDTO userLoginDTO);

  void logout();

}
