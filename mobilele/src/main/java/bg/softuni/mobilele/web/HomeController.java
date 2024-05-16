package bg.softuni.mobilele.web;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  private final Gson gson;

  public HomeController(Gson gson) {
    this.gson = gson;
  }

  @GetMapping("/")
  public String home() {

    System.out.println(gson.toJson(new Student("Pesho", 32)));

    return "index";
  }
}
