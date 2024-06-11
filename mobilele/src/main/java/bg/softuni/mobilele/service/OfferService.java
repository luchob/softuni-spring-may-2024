package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.AddOfferDTO;
import bg.softuni.mobilele.model.OfferDetailsDTO;
import bg.softuni.mobilele.model.OfferSummaryDTO;
import java.util.List;

public interface OfferService {

  long createOrder(AddOfferDTO addOfferDTO);

  OfferDetailsDTO getOfferDetails(Long id);

  List<OfferSummaryDTO> getAllOffers();

  void delete(Long offerId);
}
