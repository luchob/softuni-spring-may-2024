package bg.softuni.mobilele.offers.service;

import bg.softuni.mobilele.offers.model.dto.AddOfferDTO;
import bg.softuni.mobilele.offers.model.dto.OfferDTO;
import java.util.List;

public interface OfferService {

  void createOffer(AddOfferDTO addOfferDTO);

  void deleteOffer(Long offerId);

  OfferDTO getOfferById(Long id);

  List<OfferDTO> getAllOffers();

}
