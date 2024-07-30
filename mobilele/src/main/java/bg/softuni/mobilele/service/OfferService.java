package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.AddOfferDTO;
import bg.softuni.mobilele.model.dto.OfferDetailsDTO;
import bg.softuni.mobilele.model.dto.OfferSummaryDTO;
import bg.softuni.mobilele.model.dto.PageData;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

public interface OfferService {

  void createOffer(AddOfferDTO addOfferDTO);

  void deleteOffer(long offerId);

  OfferDetailsDTO getOfferDetails(Long id);

  PageData<OfferSummaryDTO> getAllOffersSummary(Pageable pageable);
}
