package bg.softuni.mobilele.web;

import bg.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OfferDetailController {

  private final OfferService offerService;

  public OfferDetailController(OfferService offerService) {
    this.offerService = offerService;
  }

  @GetMapping("/{id}")
  public String offerDetails(@PathVariable("id") Long id,
      Model model) {

    model.addAttribute("offerDetails", offerService.getOfferDetails(id));

    return "details";
  }

}
