package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.AddOfferDTO;
import bg.softuni.mobilele.model.OfferDetailsDTO;

public interface OfferService {

  long createOrder(AddOfferDTO addOfferDTO);

  OfferDetailsDTO getOfferDetails(Long id);
}
