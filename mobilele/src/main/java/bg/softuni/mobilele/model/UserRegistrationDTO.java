package bg.softuni.mobilele.model;

import bg.softuni.mobilele.model.validation.FieldMatch;

@FieldMatch(
    first = "password",
    second = "passwordConfirm",
    message = "{register.user.passwords.match}"
)
public class UserRegistrationDTO {

  private String firstName;
  private String lastName;
  private String password;
  private String passwordConfirm;
  private String email;

  public String getFirstName() {
    return firstName;
  }

  public UserRegistrationDTO setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserRegistrationDTO setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserRegistrationDTO setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getPasswordConfirm() {
    return passwordConfirm;
  }

  public UserRegistrationDTO setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserRegistrationDTO setEmail(String email) {
    this.email = email;
    return this;
  }
}
