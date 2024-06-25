package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.AddOfferDTO;
import bg.softuni.mobilele.model.dto.OfferDetailsDTO;
import bg.softuni.mobilele.model.dto.OfferSummaryDTO;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.service.ExRateService;
import bg.softuni.mobilele.service.OfferService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;
  private final ExRateService exRateService;

  public OfferServiceImpl(OfferRepository offerRepository,
      ExRateService exRateService) {
    this.offerRepository = offerRepository;
    this.exRateService = exRateService;
  }

  @Override
  public long createOffer(AddOfferDTO addOfferDTO) {
    return offerRepository.save(map(addOfferDTO)).getId();
  }

  @Override
  public void deleteOffer(long orderId) {
    offerRepository.deleteById(orderId);
  }

  @Override
  public OfferDetailsDTO getOfferDetails(Long id) {

    return this.offerRepository
        .findById(id)
        .map(this::toOfferDetails)
        .orElseThrow();
  }

  @Override
  public List<OfferSummaryDTO> getAllOffersSummary() {
    return offerRepository
        .findAll()
        .stream()
        .map(OfferServiceImpl::toOfferSummary)
        .toList();
  }

  private static OfferSummaryDTO toOfferSummary(OfferEntity offerEntity) {
    // todo use mapping library
    return new OfferSummaryDTO(offerEntity.getId(),
        offerEntity.getDescription(),
        offerEntity.getMileage(),
        offerEntity.getEngine());
  }


  private OfferDetailsDTO toOfferDetails(OfferEntity offerEntity) {
    // todo use mapping library
    return new OfferDetailsDTO(offerEntity.getId(),
        offerEntity.getDescription(),
        offerEntity.getMileage(),
        offerEntity.getPrice(),
        offerEntity.getEngine(),
        exRateService.allSupportedCurrencies()
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
