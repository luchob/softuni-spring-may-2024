package bg.softuni.mobilele.offers.model.dto;

import bg.softuni.mobilele.offers.model.enums.EngineTypeEnum;

public record AddOfferDTO(
    String description,
    Integer mileage,
    Integer price,
    EngineTypeEnum engineType
) {
}
