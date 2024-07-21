package bg.softuni.mobilele.offers.service.impl;

import bg.softuni.mobilele.offers.model.dto.AddOfferDTO;
import bg.softuni.mobilele.offers.model.dto.OfferDTO;
import bg.softuni.mobilele.offers.model.entity.OfferEntity;
import bg.softuni.mobilele.offers.model.enums.UserRoleEnum;
import bg.softuni.mobilele.offers.repository.OfferRepository;
import bg.softuni.mobilele.offers.service.OfferService;
import bg.softuni.mobilele.offers.service.exception.ObjectNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service(
    value = "offerService"
)
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;

  public OfferServiceImpl(OfferRepository offerRepository) {
    this.offerRepository = offerRepository;
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
  public boolean isOwner(Long offerId, UserDetails userDetails) {

    boolean isAdmin =
        userDetails
            .getAuthorities()
            .stream()
            .anyMatch(a -> Objects.equals(a.getAuthority(), "ROLE_" + UserRoleEnum.ADMIN));

    if (isAdmin) {
      return true;
    }

    return offerRepository
        .findById(offerId)
        .filter(o -> Objects.equals(o.getUuid().toString(), userDetails.getUsername()))
        .isPresent();

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
