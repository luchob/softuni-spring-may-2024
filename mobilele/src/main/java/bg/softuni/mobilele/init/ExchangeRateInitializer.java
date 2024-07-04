package bg.softuni.mobilele.init;

import bg.softuni.mobilele.service.ExRateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "forex.api.init-exchange-rates", havingValue = "true")
public class ExchangeRateInitializer implements CommandLineRunner {

  private final ExRateService exRateService;

  public ExchangeRateInitializer(ExRateService exRateService) {
    this.exRateService = exRateService;
  }

  @Override
  public void run(String... args) throws Exception {
    if (!exRateService.hasInitializedExRates()) {
      exRateService.updateRates(
          exRateService.fetchExRates()
      );
    }
  }
}
