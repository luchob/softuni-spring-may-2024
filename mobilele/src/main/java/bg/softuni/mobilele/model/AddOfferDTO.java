package bg.softuni.mobilele.model;

import bg.softuni.mobilele.model.enums.EngineTypeEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public record AddOfferDTO(EngineTypeEnum engineType,
                          @Size(min = 5, message="Min 5")
                          @NotEmpty(message = "{add.offer.description.not.empty}") String description) {
  public static AddOfferDTO empty() {
    return new AddOfferDTO(null, null);
  }
}
