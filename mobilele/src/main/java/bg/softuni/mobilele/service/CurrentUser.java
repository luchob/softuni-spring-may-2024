package bg.softuni.mobilele.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

  private String fullName;

  private boolean isLoggedIn;

  public String getFullName() {
    return fullName;
  }

  public CurrentUser setFullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  public boolean isLoggedIn() {
    return isLoggedIn;
  }

  public CurrentUser setLoggedIn(boolean loggedIn) {
    isLoggedIn = loggedIn;
    return this;
  }

  public void clean() {
    this.fullName = null;
    this.isLoggedIn = false;
  }
}
