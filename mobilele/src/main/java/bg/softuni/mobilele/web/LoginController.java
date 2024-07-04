package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.UserLoginDTO;
import bg.softuni.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class LoginController {

  @GetMapping("/login")
  public String login() {
    return "auth-login";
  }

  @PostMapping("/users/login-error")
  public String failedLogin(Model model) {
    return "auth-login";
  }
}
