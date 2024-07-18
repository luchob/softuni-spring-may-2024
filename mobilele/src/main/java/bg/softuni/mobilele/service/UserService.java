package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.UserLoginDTO;
import bg.softuni.mobilele.model.dto.UserRegistrationDTO;
import bg.softuni.mobilele.model.user.MobileleUserDetails;
import java.util.Optional;

public interface UserService {

  void registerUser(UserRegistrationDTO userRegistration);

  Optional<MobileleUserDetails> getCurrentUser();
}
