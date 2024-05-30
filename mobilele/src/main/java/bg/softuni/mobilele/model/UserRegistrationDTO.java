package bg.softuni.mobilele.model;

public record UserRegistrationDTO(String firstName,
                                  String lastName,
                                  String password,
                                  String email) {

  public static UserRegistrationDTO empty() {
    return new UserRegistrationDTO(null, null, null, null);
  }

}
