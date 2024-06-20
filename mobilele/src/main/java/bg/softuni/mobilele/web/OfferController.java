package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.AddOfferDTO;
import bg.softuni.mobilele.model.enums.EngineTypeEnum;
import bg.softuni.mobilele.service.ExRateService;
import bg.softuni.mobilele.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offers")
public class OfferController {

  private final OfferService offerService;
  private final ExRateService exRateService;

  public OfferController(OfferService offerService,
      ExRateService exRateService) {
    this.offerService = offerService;
    this.exRateService = exRateService;
  }

  @ModelAttribute("allEngineTypes")
  public EngineTypeEnum[] allEngineTypes() {
    return EngineTypeEnum.values();
  }

  @GetMapping("/add")
  public String newOffer(Model model) {

    if (!model.containsAttribute("addOfferDTO")) {
      model.addAttribute("addOfferDTO", AddOfferDTO.empty());
    }

    return "offer-add";
  }

  @PostMapping("add")
  public String createOffer(
      @Valid AddOfferDTO addOfferDTO,
      BindingResult bindingResult,
      RedirectAttributes rAtt) {

    if(bindingResult.hasErrors()){
      rAtt.addFlashAttribute("addOfferDTO", addOfferDTO);
      rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);
      return "redirect:/offers/add";
    }


    long newOfferId = offerService.createOffer(addOfferDTO);

    return "redirect:/offers/" + newOfferId;
  }

  @GetMapping("/{id}")
  public String offerDetails(@PathVariable("id") Long id,
      Model model) {

    model.addAttribute("allCurrencies", exRateService.getAllSupportedCurrencties());
    model.addAttribute("offerDetails", offerService.getOfferDetails(id));

    return "details";
  }

  @DeleteMapping("/{id}")
  public String deleteOffer(@PathVariable("id") Long id) {

    offerService.deleteOffer(id);

    return "redirect:/offers/all";
  }
}
