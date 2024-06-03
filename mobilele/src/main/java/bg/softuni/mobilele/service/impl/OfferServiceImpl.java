package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.AddOfferDTO;
import bg.softuni.mobilele.model.OfferDetailsDTO;
import bg.softuni.mobilele.model.mapper.OfferMapper;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;
  private final OfferMapper mapper;

  public OfferServiceImpl(OfferRepository offerRepository,
      OfferMapper mapper) {
    this.offerRepository = offerRepository;
    this.mapper = mapper;
  }

  @Override
  public long createOrder(AddOfferDTO addOfferDTO) {
    return offerRepository
        .save(mapper.addOfferDTOtoOfferEntity((addOfferDTO)))
        .getId();
  }

  @Override
  public OfferDetailsDTO getOfferDetails(Long id) {

    return this.offerRepository
        .findById(id)
        .map(mapper::offerEntityToOfferDetails)
        .orElseThrow();
  }
}
