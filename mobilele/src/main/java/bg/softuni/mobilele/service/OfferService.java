package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.AddOfferDTO;
import bg.softuni.mobilele.model.dto.OfferDetailsDTO;
import bg.softuni.mobilele.model.dto.OfferSummaryDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

public interface OfferService {

  OfferDetailsDTO createOffer(AddOfferDTO addOfferDTO);

  void deleteOffer(long offerId);

  OfferDetailsDTO getOfferDetails(Long id);

  Page<OfferSummaryDTO> getAllOffersSummary(Pageable pageable);
}
