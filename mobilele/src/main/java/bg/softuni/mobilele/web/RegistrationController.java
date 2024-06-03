package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.UserRegistrationDTO;
import bg.softuni.mobilele.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class RegistrationController {

  private final UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @ModelAttribute("registerDTO")
  UserRegistrationDTO registerDTO() {
    return new UserRegistrationDTO();
  }

  @GetMapping("/register")
  public String register() {
    return "auth-register";
  }

  @PostMapping("/register")
  public String register(@Valid UserRegistrationDTO registerDTO,
      BindingResult bindingResult,
      RedirectAttributes rAtt) {

    if(bindingResult.hasErrors()){
      rAtt.addFlashAttribute("registerDTO", registerDTO);
      rAtt.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);
      return "redirect:/users/register";
    }

    userService.registerUser(registerDTO);

    return "index";
  }
}
