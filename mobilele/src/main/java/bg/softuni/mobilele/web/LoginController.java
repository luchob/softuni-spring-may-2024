package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.UserLogingDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

  @PostMapping("/users/login")
  public String login(UserLogingDTO userLogingDTO) {

    System.out.println(userLogingDTO);

    return "redirect:/";
  }

  @GetMapping("/users/login")
  public String login(Model map) {
    return "auth-login";
  }

}
