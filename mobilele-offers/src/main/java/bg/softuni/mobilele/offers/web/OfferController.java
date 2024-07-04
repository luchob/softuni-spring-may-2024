package bg.softuni.mobilele.offers.web;

import bg.softuni.mobilele.offers.model.dto.AddOfferDTO;
import bg.softuni.mobilele.offers.model.dto.OfferDTO;
import bg.softuni.mobilele.offers.service.OfferService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offers")
public class OfferController {

  private static final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);
  private final OfferService offerService;

  public OfferController(OfferService offerService) {
    this.offerService = offerService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<OfferDTO> findById(@PathVariable("id") Long id) {
    return ResponseEntity
        .ok(offerService.getOfferById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<OfferDTO> deleteById(@PathVariable("id") Long id) {
    offerService.deleteOffer(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<List<OfferDTO>> getAllOffers() {
    return ResponseEntity.ok(
        offerService.getAllOffers()
    );
  }

  @PostMapping
  public ResponseEntity<OfferDTO> createOffer(
      @RequestBody AddOfferDTO addOfferDTO
  ) {
    LOGGER.info("Going to create an offer {}", addOfferDTO);

    offerService.createOffer(addOfferDTO);
    return ResponseEntity.ok().build();
  }

}
