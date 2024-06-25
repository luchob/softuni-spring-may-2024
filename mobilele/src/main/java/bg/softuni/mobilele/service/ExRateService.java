package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.ExRatesDTO;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ExRateService {

  List<String> allSupportedCurrencies();
  boolean hasInitializedExRates();

  ExRatesDTO fetchExRates();

  void updateRates(ExRatesDTO exRatesDTO);

  Optional<BigDecimal> findExRate(String from, String to);

  BigDecimal convert(String from, String to, BigDecimal amount);
}
