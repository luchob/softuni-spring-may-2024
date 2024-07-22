package bg.softuni.mobilele.offers.service;

import bg.softuni.mobilele.offers.model.dto.AddOfferDTO;
import bg.softuni.mobilele.offers.model.dto.OfferDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.security.core.userdetails.UserDetails;

public interface OfferService {

  OfferDTO createOffer(AddOfferDTO addOfferDTO);

  void deleteOffer(Long offerId);

  OfferDTO getOfferById(Long id);

  List<OfferDTO> getAllOffers();
  boolean isOwner(Long offerId, UserDetails userDetails);
}
