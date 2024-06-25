package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.EngineTypeEnum;
import java.util.List;

public record OfferDetailsDTO(Long id,
                              String description,
                              Integer mileage,
                              Integer price,
                              EngineTypeEnum engineType,
                              List<String> allCurrencies) {

}
