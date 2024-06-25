package bg.softuni.mobilele.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {
  @NotEmpty
  @Size(min = 5, max = 20)
  private String firstName;
  @NotEmpty
  @Size(min = 5, max = 20)
  private String lastName;
  @NotEmpty
  private String password;
  @NotEmpty
  @Email
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

  public String getEmail() {
    return email;
  }

  public UserRegistrationDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  @Override
  public String toString() {
    return "UserRegistrationDTO{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", password='" + (password == null ? "N/A" : "[PROVIDED]") + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}
