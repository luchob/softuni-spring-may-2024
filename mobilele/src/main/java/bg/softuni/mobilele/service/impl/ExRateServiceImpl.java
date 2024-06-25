package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.config.ForexApiConfig;
import bg.softuni.mobilele.model.dto.ExRatesDTO;
import bg.softuni.mobilele.model.entity.ExRateEntity;
import bg.softuni.mobilele.repository.ExRateRepository;
import bg.softuni.mobilele.service.ExRateService;
import bg.softuni.mobilele.service.ObjectNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ExRateServiceImpl implements ExRateService {

  private final Logger LOGGER = LoggerFactory.getLogger(ExRateServiceImpl.class);
  private final ExRateRepository exRateRepository;
  private final RestClient restClient;
  private final ForexApiConfig forexApiConfig;

  public ExRateServiceImpl(ExRateRepository exRateRepository,
      RestClient restClient,
      ForexApiConfig forexApiConfig) {
    this.exRateRepository = exRateRepository;
    this.restClient = restClient;
    this.forexApiConfig = forexApiConfig;
  }

  @Override
  public List<String> allSupportedCurrencies() {
    return exRateRepository
        .findAll()
        .stream()
        .map(ExRateEntity::getCurrency)
        .toList();
  }

  @Override
  public boolean hasInitializedExRates() {
    return exRateRepository.count() > 0;
  }

  @Override
  public ExRatesDTO fetchExRates() {
    return restClient
        .get()
        .uri(forexApiConfig.getUrl(), forexApiConfig.getKey())
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .body(ExRatesDTO.class);
  }

  @Override
  public void updateRates(ExRatesDTO exRatesDTO) {
    LOGGER.info("Updating {} rates.", exRatesDTO.rates().size());

    if (!forexApiConfig.getBase().equals(exRatesDTO.base())) {
      throw new IllegalArgumentException("The exchange rates that should be updated are not based on " +
          forexApiConfig.getBase() + " but rather on " + exRatesDTO.base());
    }

    exRatesDTO.rates().forEach((currency, rate) -> {
      var exRateEntity = exRateRepository.findByCurrency(currency)
          .orElseGet(() -> new ExRateEntity().setCurrency(currency));

      exRateEntity.setRate(rate);

      exRateRepository.save(exRateEntity);
    });
  }

  @Override
  public Optional<BigDecimal> findExRate(String from, String to) {

    if (Objects.equals(from, to)) {
      return Optional.of(BigDecimal.ONE);
    }

    // USD/BGN=x
    // USD/EUR=y

    // USD = x * BGN
    // USD = y * EUR

    // EUR/BGN = x / y

    Optional<BigDecimal> fromOpt = forexApiConfig.getBase().equals(from) ?
        Optional.of(BigDecimal.ONE) :
        exRateRepository.findByCurrency(from).map(ExRateEntity::getRate);

    Optional<BigDecimal> toOpt = forexApiConfig.getBase().equals(to) ?
        Optional.of(BigDecimal.ONE) :
        exRateRepository.findByCurrency(to).map(ExRateEntity::getRate);

    if (fromOpt.isEmpty() || toOpt.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(toOpt.get().divide(fromOpt.get(), 2, RoundingMode.HALF_DOWN));
    }
  }

  @Override
  public BigDecimal convert(String from, String to, BigDecimal amount) {
    return findExRate(from, to)
        .orElseThrow(() -> new ObjectNotFoundException("Conversion from " + from + " to " + to + " not possible!"))
        .multiply(amount);
  }
}
