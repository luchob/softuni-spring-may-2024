package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.UserLoginDTO;
import bg.softuni.mobilele.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class LoginController {

  private final UserService userService;

  public LoginController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/login")
  public String login() {
    return "auth-login";
  }

  @PostMapping("/login-error")
  public String loginError(
      Model model,
      @ModelAttribute("email") String email
  ) {
    return "auth-login";
  }
}
