package bg.softuni.mobilele.model;

import bg.softuni.mobilele.model.enums.EngineTypeEnum;

public record OfferSummaryDTO(Long id,
                              String description,
                              Integer mileage,
                              EngineTypeEnum engineType) {

}
