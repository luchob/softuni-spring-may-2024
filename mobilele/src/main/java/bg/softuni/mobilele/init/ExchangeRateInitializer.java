package bg.softuni.mobilele.init;

import bg.softuni.mobilele.service.ExRateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
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
