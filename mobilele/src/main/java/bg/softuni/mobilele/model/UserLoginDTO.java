package bg.softuni.mobilele.model;

public class UserLoginDTO {

  private String email;
  private String password;

  public String getEmail() {
    return email;
  }

  public UserLoginDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserLoginDTO setPassword(String password) {
    this.password = password;
    return this;
  }
}
