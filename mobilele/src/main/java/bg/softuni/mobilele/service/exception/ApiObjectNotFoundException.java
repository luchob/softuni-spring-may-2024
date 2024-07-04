package bg.softuni.mobilele.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ApiObjectNotFoundException extends RuntimeException{
  private final Object id;

  public ApiObjectNotFoundException(String message, Object id) {
    super(message);
    this.id = id;
  }

  public Object getId() {
    return id;
  }
}
