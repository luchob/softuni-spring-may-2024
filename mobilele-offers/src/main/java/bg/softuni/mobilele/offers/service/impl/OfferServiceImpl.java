package bg.softuni.mobilele.offers.service.impl;

import bg.softuni.mobilele.offers.model.dto.AddOfferDTO;
import bg.softuni.mobilele.offers.model.dto.OfferDTO;
import bg.softuni.mobilele.offers.model.entity.OfferEntity;
import bg.softuni.mobilele.offers.model.mapper.OfferMapper;
import bg.softuni.mobilele.offers.repository.OfferRepository;
import bg.softuni.mobilele.offers.service.OfferService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;
  private final OfferMapper offerMapper;

  public OfferServiceImpl(OfferRepository offerRepository,
      OfferMapper offerMapper) {
    this.offerRepository = offerRepository;
    this.offerMapper = offerMapper;
  }

  @Override
  public OfferDTO createOffer(AddOfferDTO addOfferDTO, String userId) {
    OfferEntity newOfferEntity = offerMapper.map(addOfferDTO).setUserId(userId);
    OfferEntity savedOfferEntity = offerRepository.save(newOfferEntity);

    return offerMapper.map(savedOfferEntity);
  }

  @Override
  public OfferDTO getOfferById(Long id) {
    return offerRepository
        .findById(id)
        .map(offerMapper::map)
        .orElseThrow(() -> new IllegalArgumentException("Not found!"));
  }

  @Override
  public void deleteOffer(Long offerId) {
    offerRepository.deleteById(offerId);
  }

  @Override
  public Page<OfferDTO> getAllOffers(Pageable pageable) {
    return offerRepository
        .findAll(pageable)
        .map(offerMapper::map);
  }
}
