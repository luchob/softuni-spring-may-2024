package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.AddOfferDTO;
import bg.softuni.mobilele.model.dto.ErrorDTO;
import bg.softuni.mobilele.model.dto.ExRatesDTO;
import bg.softuni.mobilele.model.dto.OfferDetailsDTO;
import bg.softuni.mobilele.model.dto.OfferSummaryDTO;
import bg.softuni.mobilele.model.dto.PageResponse;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.service.ExRateService;
import bg.softuni.mobilele.service.OfferService;
import bg.softuni.mobilele.service.exception.ObjectNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.data.web.PagedModel;

@Service
public class OfferServiceImpl implements OfferService {

  private Logger LOGGER = LoggerFactory.getLogger(OfferServiceImpl.class);
  private final RestClient offerRestClient;
  private final OfferRepository offerRepository;
  private final ObjectMapper objectMapper;

  public OfferServiceImpl(
      @Qualifier("offersRestClient") RestClient offerRestClient,
      OfferRepository offerRepository,
      ObjectMapper objectMapper) {
    this.offerRestClient = offerRestClient;
    this.offerRepository = offerRepository;
    this.objectMapper = objectMapper;
  }

  @Override
  public OfferDetailsDTO createOffer(AddOfferDTO addOfferDTO) {
    LOGGER.info("Creating new offer...");

    return offerRestClient
        .post()
        .uri("/offers")
        .body(addOfferDTO)
        .retrieve()
        .body(OfferDetailsDTO.class);
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
  public Page<OfferSummaryDTO> getAllOffersSummary(Pageable pageable) {
    LOGGER.info("Get all offers...");

    PageResponse<OfferSummaryDTO> offers = offerRestClient
        .get()
        .uri("/offers?page={page}&size={size}&sort=id,desc",
            pageable.getPageNumber(),
            pageable.getPageSize()
        )
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .body(new ParameterizedTypeReference<>(){});

    assert offers != null;

    return new PageImpl<>(offers.getContent(), pageable, offers.getPage().totalElements());
  }
}
