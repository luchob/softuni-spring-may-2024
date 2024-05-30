package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.AddOfferDTO;
import bg.softuni.mobilele.model.OfferDetailsDTO;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;

  public OfferServiceImpl(OfferRepository offerRepository) {
    this.offerRepository = offerRepository;
  }

  @Override
  public long createOrder(AddOfferDTO addOfferDTO) {
    return offerRepository.save(map(addOfferDTO)).getId();
  }

  @Override
  public OfferDetailsDTO getOfferDetails(Long id) {

    return this.offerRepository
        .findById(id)
        .map(OfferServiceImpl::toOfferDetails)
        .orElseThrow();
  }

  private static OfferDetailsDTO toOfferDetails(OfferEntity offerEntity) {
    // todo use mapping library
    return new OfferDetailsDTO(offerEntity.getId(),
        offerEntity.getDescription(),
        offerEntity.getMileage(),
        offerEntity.getEngine());
  }

  private static OfferEntity map(AddOfferDTO addOfferDTO) {
    // todo - use mapped (e.g. ModelMapper)
    return new OfferEntity()
        .setDescription(addOfferDTO.description())
        .setEngine(addOfferDTO.engineType())
        .setMileage(addOfferDTO.mileage());
  }
}
