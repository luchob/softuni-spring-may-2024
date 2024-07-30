package bg.softuni.mobilele.web;

import bg.softuni.mobilele.service.OfferService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OffersController {

  private final OfferService offerService;

  public OffersController(OfferService offerService) {
    this.offerService = offerService;
  }

  @GetMapping("/all")
  public String getAllOffers(Model model,
      @PageableDefault(
          size = 3,
          sort="id",
          direction = Direction.DESC
      ) Pageable pageable) {

    model.addAttribute("allOffers", offerService.getAllOffersSummary(pageable));
    return "offers";
  }

}
