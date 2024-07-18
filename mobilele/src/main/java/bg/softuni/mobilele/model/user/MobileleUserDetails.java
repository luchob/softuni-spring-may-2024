package bg.softuni.mobilele.model.user;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MobileleUserDetails extends User {

  private final UUID uuid;
  private final String firstName;
  private final String lastName;

  public MobileleUserDetails(
      UUID uuid,
      String username,
      String password,
      Collection<? extends GrantedAuthority> authorities,
      String firstName,
      String lastName
  ) {
    super(username, password, authorities);
    this.uuid = uuid;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public UUID getUuid() {
    return uuid;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFullName() {
    StringBuilder fullName = new StringBuilder();
    if (firstName != null) {
      fullName.append(firstName);
    }
    if (lastName != null) {
      if (!fullName.isEmpty()) {
        fullName.append(" ");
      }
      fullName.append(lastName);
    }

    return fullName.toString();
  }
}
