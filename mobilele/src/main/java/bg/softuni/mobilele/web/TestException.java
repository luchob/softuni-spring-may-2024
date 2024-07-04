package bg.softuni.mobilele.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TestException extends RuntimeException{

  private final String code;

  public TestException(String message, String code) {
    super(message);
    this.code = code;
  }
}
