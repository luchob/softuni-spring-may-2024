package bg.softuni.mobilele.init;

import bg.softuni.mobilele.repository.ExRateRepository;
import bg.softuni.mobilele.service.ExRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExRatesInit implements CommandLineRunner {

  private final Logger LOGGER = LoggerFactory.getLogger(ExRatesInit.class);
  private final ExRateService exRateService;

  public ExRatesInit(ExRateService exRateService) {

    this.exRateService = exRateService;
  }

  @Override
  public void run(String... args) throws Exception {
    try {
      if (!exRateService.hasInitializedExRates()) {
        exRateService.updateRates(exRateService.fetchExRates());
      }
    } catch (Exception ex) {
      LOGGER.error("Unable to fetch and update ex rates.", ex);
    }
  }
}
