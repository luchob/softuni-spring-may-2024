package bg.softuni.mobilele.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/test")
  public Student test() {

    //return "Hello from test controller";
  }

  record Student(String name, int age) {
  }

}
