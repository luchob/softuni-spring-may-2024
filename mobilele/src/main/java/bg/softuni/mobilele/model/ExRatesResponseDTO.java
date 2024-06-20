package bg.softuni.mobilele.model;

import java.math.BigDecimal;
import java.util.Map;

public record ExRatesResponseDTO(String base, Map<String, BigDecimal> rates) {
  /**
   {
   "base": "USD",
   "rates": {
   "AED": 3.6728,
   "AFN": 70.240734,
   }
   }
   */
}
