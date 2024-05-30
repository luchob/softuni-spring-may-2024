package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.AddOfferDTO;
import bg.softuni.mobilele.model.enums.EngineTypeEnum;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/offers")
@Controller
public class OfferController {

  @ModelAttribute("allEngineTypes")
  EngineTypeEnum[] allEngineTypes() {
    return EngineTypeEnum.values();
  }

  @GetMapping("/add")
  public String add(ModelMap model) {
    if (!model.containsAttribute("addOfferDTO")) {
      model.addAttribute("addOfferDTO", AddOfferDTO.empty());
    }
    return "offer-add";
  }

  @PostMapping("/add")
  public String add(@Valid AddOfferDTO addOfferDTO,
      BindingResult bindingResult,
      RedirectAttributes rAtt) {

    rAtt.addFlashAttribute("addOfferDTO", addOfferDTO);
    rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);
    return "redirect:/offers/add";

  }

}
