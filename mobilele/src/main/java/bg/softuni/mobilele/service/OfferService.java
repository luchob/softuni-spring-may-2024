package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.AddOfferDTO;
import bg.softuni.mobilele.model.OfferDetailsDTO;
import bg.softuni.mobilele.model.OfferSummaryDTO;
import java.util.List;

public interface OfferService {

  long createOffer(AddOfferDTO addOfferDTO, String sellerEmail);

  void deleteOffer(long orderId);

  OfferDetailsDTO getOfferDetails(Long id);

  List<OfferSummaryDTO> getAllOffersSummary();
}
