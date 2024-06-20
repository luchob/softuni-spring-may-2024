package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.config.ForexApiConfig;
import bg.softuni.mobilele.model.ExRatesResponseDTO;
import bg.softuni.mobilele.model.entity.ExRateEntity;
import bg.softuni.mobilele.repository.ExRateRepository;
import bg.softuni.mobilele.service.ExRateService;
import bg.softuni.mobilele.web.ObjectNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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
  private static final Logger LOGGER = LoggerFactory.getLogger(ExRateServiceImpl.class);

  private final ForexApiConfig forexApiConfiguration;
  private final ExRateRepository exRateRepository;

  public ExRateServiceImpl(ForexApiConfig forexApiConfiguration,
      ExRateRepository exRateRepository) {
    this.forexApiConfiguration = forexApiConfiguration;
    this.exRateRepository = exRateRepository;
  }

  @Override
  public List<String> getAllSupportedCurrencties() {
    if (hasInitializedExRates()) {

      List<String> allCurrencies = new ArrayList<>();
      allCurrencies.add(forexApiConfiguration.getBase());

      allCurrencies.addAll(this.exRateRepository.findAll().stream().
          map(ExRateEntity::getCurrency)
          .toList());

      return allCurrencies;

    } else {
      return List.of();
    }
  }

  @Override
  public boolean hasInitializedExRates() {
    return exRateRepository.count() > 0;
  }

  @Override
  public ExRatesResponseDTO fetchExRates() {

    RestClient restClient = RestClient.create();

    return restClient.
        get().
        uri(forexApiConfiguration.getUrl(), forexApiConfiguration.getKey()).
        accept(MediaType.APPLICATION_JSON).
        retrieve().
        body(ExRatesResponseDTO.class);
  }

  @Override
  public void updateRates(ExRatesResponseDTO exRates) {
    LOGGER.info("Processing {} exchange rates.", exRates.rates().size());

    if (!Objects.equals(forexApiConfiguration.getBase(), exRates.base())) {
      throw new IllegalArgumentException("The base currency should be: " +
          forexApiConfiguration.getBase());
    }

    exRates.rates().forEach((currency, rate) -> {
      var exRateEntity = exRateRepository.
          findByCurrency(currency).
          orElse(new ExRateEntity().setCurrency(currency));

      exRateEntity.setRate(rate);

      exRateRepository.save(exRateEntity);
    });
  }

  @Override
  public Optional<BigDecimal> findExRate(String from, String to) {

    if (Objects.equals(from, to)) {
      return Optional.of(BigDecimal.ONE);
    }

    // USD/BGN = x
    // USD/EUR = y

    // USD = x * BGN
    // USD = y * EUR

    // EUR/BGN = x / y

    Optional<BigDecimal> fromOpt = forexApiConfiguration.getBase().equals(from) ?
        Optional.of(BigDecimal.ONE) :
        exRateRepository.findByCurrency(from).map(ExRateEntity::getRate);

    Optional<BigDecimal> toOpt = forexApiConfiguration.getBase().equals(to) ?
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
        .orElseThrow(ObjectNotFoundException::new)
        .multiply(amount);
  }
}
