package bg.softuni.mobilele.offers.service;

import bg.softuni.mobilele.offers.model.dto.AddOfferDTO;
import bg.softuni.mobilele.offers.model.dto.OfferDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OfferService {

  OfferDTO createOffer(AddOfferDTO addOfferDTO, String userId);

  void deleteOffer(Long offerId);

  OfferDTO getOfferById(Long id);

  Page<OfferDTO> getAllOffers(Pageable pageable);

}
