package bg.softuni.mobilele.offers.service;

import bg.softuni.mobilele.offers.model.dto.AddOfferDTO;
import bg.softuni.mobilele.offers.model.dto.OfferDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.security.core.userdetails.UserDetails;

public interface OfferService {

  OfferDTO createOffer(AddOfferDTO addOfferDTO);

  void deleteOffer(UserDetails userDetails, Long offerId);

  OfferDTO getOfferById(Long id);

  PagedModel<OfferDTO> getAllOffers(Pageable pageable);

  void cleanupOldOffers();

  boolean isOwner(UserDetails userDetails, Long offerId);
}
