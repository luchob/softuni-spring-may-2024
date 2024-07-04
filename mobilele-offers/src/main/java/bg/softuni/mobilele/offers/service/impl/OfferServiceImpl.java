package bg.softuni.mobilele.offers.service.impl;

import bg.softuni.mobilele.offers.model.dto.AddOfferDTO;
import bg.softuni.mobilele.offers.model.dto.OfferDTO;
import bg.softuni.mobilele.offers.model.entity.OfferEntity;
import bg.softuni.mobilele.offers.repository.OfferRepository;
import bg.softuni.mobilele.offers.service.OfferService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;

  public OfferServiceImpl(OfferRepository offerRepository) {
    this.offerRepository = offerRepository;
  }

  @Override
  public void createOffer(AddOfferDTO addOfferDTO) {
    offerRepository.save(map(addOfferDTO));
  }

  @Override
  public OfferDTO getOfferById(Long id) {
    return offerRepository
        .findById(id)
        .map(OfferServiceImpl::map)
        .orElseThrow(() -> new IllegalArgumentException("Not found!"));
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
