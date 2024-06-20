package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.ExRatesResponseDTO;
import bg.softuni.mobilele.model.entity.ExRateEntity;
import bg.softuni.mobilele.web.ObjectNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

public interface ExRateService {

  List<String> getAllSupportedCurrencties();

  boolean hasInitializedExRates();
  ExRatesResponseDTO fetchExRates();

  void updateRates(ExRatesResponseDTO exRates);

  Optional<BigDecimal> findExRate(String from, String to);

  BigDecimal convert(String from, String to, BigDecimal amount);
}
