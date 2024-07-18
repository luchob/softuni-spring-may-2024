package bg.softuni.mobilele.offers.model.mapper;

import bg.softuni.mobilele.offers.model.dto.AddOfferDTO;
import bg.softuni.mobilele.offers.model.dto.OfferDTO;
import bg.softuni.mobilele.offers.model.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

  @Mapping(target = "engine", source = "engineType")
  OfferEntity map(AddOfferDTO registerDTO);
  @Mapping(target = "engineType", source = "engine")
  OfferDTO map(OfferEntity offerEntity);
}