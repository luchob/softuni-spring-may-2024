package bg.softuni.state.web;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionController {

  private static final String LANG_SESSION_NAME = "lang";
  private static final String DEFAULT_LANG = "en";

  @GetMapping("/session")
  public String cookies(
      HttpSession session,
      Model model) {

    Object lang = session.getAttribute(LANG_SESSION_NAME);

    model.addAttribute("lang",
        lang != null ? lang : DEFAULT_LANG);

    return "session";
  }

  @PostMapping("/session")
  public String cookies(
      @RequestParam("language") String lang,
      HttpSession session
  ) {
    session.setAttribute(LANG_SESSION_NAME, lang);

    return "redirect:/session";
  }

}
