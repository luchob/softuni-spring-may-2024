package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.AddOfferDTO;
import bg.softuni.mobilele.model.dto.ExRatesDTO;
import bg.softuni.mobilele.model.dto.OfferDetailsDTO;
import bg.softuni.mobilele.model.dto.OfferSummaryDTO;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.service.ExRateService;
import bg.softuni.mobilele.service.OfferService;
import bg.softuni.mobilele.service.exception.ObjectNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OfferServiceImpl implements OfferService {

  private Logger LOGGER = LoggerFactory.getLogger(OfferServiceImpl.class);
  private final RestClient offerRestClient;
  private final OfferRepository offerRepository;
  private final ExRateService exRateService;

  public OfferServiceImpl(
      @Qualifier("offersRestClient") RestClient offerRestClient,
      OfferRepository offerRepository,

      ExRateService exRateService) {
    this.offerRestClient = offerRestClient;
    this.offerRepository = offerRepository;
    this.exRateService = exRateService;
  }

  @Override
  public void createOffer(AddOfferDTO addOfferDTO) {
    LOGGER.info("Creating new offer...");

    offerRestClient
        .post()
        .uri("/offers")
        .body(addOfferDTO)
        .retrieve();
  }

  @Override
  public void deleteOffer(long offerId) {
    offerRepository.deleteById(offerId);
  }

  @Override
  public OfferDetailsDTO getOfferDetails(Long id) {

    return offerRestClient
        .get()
        .uri("/offers/{id}", id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .body(OfferDetailsDTO.class);
  }

  @Override
  public List<OfferSummaryDTO> getAllOffersSummary() {
    LOGGER.info("Get all offers...");

    return offerRestClient
        .get()
        .uri("/offers")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .body(new ParameterizedTypeReference<>(){});
  }
}
