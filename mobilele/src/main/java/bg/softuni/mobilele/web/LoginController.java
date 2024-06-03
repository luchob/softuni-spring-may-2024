package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.UserLoginDTO;
import bg.softuni.mobilele.service.UserService;
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

  @ModelAttribute("loginDTO")
  public UserLoginDTO loginDTO() {
    return new UserLoginDTO();
  }

  @GetMapping("/login")
  public String login() {

    return "auth-login";
  }

  @PostMapping("/login")
  public String login(UserLoginDTO userLoginDTO) {

    boolean success = userService.login(userLoginDTO);

    return success ? "redirect:/" : "auth-login";
  }

}
