package bg.softuni.mobilele.model.mapper;

import bg.softuni.mobilele.model.AddOfferDTO;
import bg.softuni.mobilele.model.OfferDetailsDTO;
import bg.softuni.mobilele.model.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

  @Mapping(source = "engineType", target = "engine")
  OfferEntity addOfferDTOtoOfferEntity(AddOfferDTO addOfferDTO);

  @Mapping(source = "engine", target = "engineType")
  OfferDetailsDTO offerEntityToOfferDetails(OfferEntity offerEntity);
}
