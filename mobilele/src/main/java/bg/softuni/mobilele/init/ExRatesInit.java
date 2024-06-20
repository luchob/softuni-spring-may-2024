package bg.softuni.mobilele.init;

import bg.softuni.mobilele.repository.ExRateRepository;
import bg.softuni.mobilele.service.ExRateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExRatesInit implements CommandLineRunner {

  private final ExRateService exRateService;

  public ExRatesInit(ExRateService exRateService) {

    this.exRateService = exRateService;
  }

  @Override
  public void run(String... args) throws Exception {
    if (!exRateService.hasInitializedExRates()) {
      exRateService.updateRates(exRateService.fetchExRates());
    }
  }
}
