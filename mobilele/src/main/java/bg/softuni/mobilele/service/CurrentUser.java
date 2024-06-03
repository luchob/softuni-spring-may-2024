package bg.softuni.mobilele.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

  private boolean logged;
  private String firstName, lastName;

  public String getFirstName() {
    return firstName;
  }

  public CurrentUser setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public CurrentUser setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public boolean isLogged() {
    return logged;
  }

  public CurrentUser setLogged(boolean logged) {
    this.logged = logged;
    return this;
  }
}
