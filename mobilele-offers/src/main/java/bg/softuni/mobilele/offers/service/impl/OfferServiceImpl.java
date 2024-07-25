package bg.softuni.mobilele.offers.service.impl;

import bg.softuni.mobilele.offers.model.dto.AddOfferDTO;
import bg.softuni.mobilele.offers.model.dto.OfferDTO;
import bg.softuni.mobilele.offers.model.entity.OfferEntity;
import bg.softuni.mobilele.offers.repository.OfferRepository;
import bg.softuni.mobilele.offers.service.OfferService;
import bg.softuni.mobilele.offers.service.exception.ObjectNotFoundException;
import java.time.Instant;
import java.time.Period;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

  private final Logger LOGGER = LoggerFactory.getLogger(OfferService.class);
  private final OfferRepository offerRepository;
  private final Period retentionPeriod;

  public OfferServiceImpl(OfferRepository offerRepository,
      @Value("${offers.retention.period}") Period retentionPeriod) {
    this.offerRepository = offerRepository;
    this.retentionPeriod = retentionPeriod;
  }

  @Override
  public OfferDTO createOffer(AddOfferDTO addOfferDTO) {
    OfferEntity offerEntity = offerRepository.save(map(addOfferDTO));
    return map(offerEntity);
  }

  @Override
  public OfferDTO getOfferById(Long id) {
    return offerRepository
        .findById(id)
        .map(OfferServiceImpl::map)
        .orElseThrow(ObjectNotFoundException::new);
  }

  @Override
  public void deleteOffer(Long offerId) {
    offerRepository.deleteById(offerId);
  }

  @Override
  public List<OfferDTO> getAllOffers() {
    return offerRepository
        .findAll()
        .stream()
        .map(OfferServiceImpl::map)
        .toList();
  }

  @Override
  public void cleanupOldOffers() {
    Instant now = Instant.now();
    Instant deleteBefore = now.minus(retentionPeriod);
    LOGGER.info("Removing all offers older than " + deleteBefore);
    offerRepository.deleteOldOffers(deleteBefore);
    LOGGER.info("Old orders were removed");
  }

  private static OfferDTO map(OfferEntity offerEntity) {
    // todo - use mapped (e.g. ModelMapper, MapStruct)
    return new OfferDTO(
        offerEntity.getId(),
        offerEntity.getDescription(),
        offerEntity.getMileage(),
        offerEntity.getPrice(),
        offerEntity.getEngine()
    );
  }

  private static OfferEntity map(AddOfferDTO addOfferDTO) {
    // todo - use mapped (e.g. ModelMapper)
    return new OfferEntity()
        .setDescription(addOfferDTO.description())
        .setEngine(addOfferDTO.engineType())
        .setMileage(addOfferDTO.mileage())
        .setPrice(addOfferDTO.price());
  }
}
