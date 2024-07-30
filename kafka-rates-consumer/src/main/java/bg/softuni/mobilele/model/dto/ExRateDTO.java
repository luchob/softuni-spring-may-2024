package bg.softuni.mobilele.model.dto;

import java.math.BigDecimal;

public record ExRateDTO(String base, String currency, BigDecimal rate) {
}
