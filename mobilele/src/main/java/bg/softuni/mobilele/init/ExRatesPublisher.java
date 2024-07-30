package bg.softuni.mobilele.init;

import bg.softuni.mobilele.service.ExRateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class ExRatesPublisher implements CommandLineRunner {

  private final ExRateService exRateService;

  public ExRatesPublisher(ExRateService exRateService) {
    this.exRateService = exRateService;
  }

  @Override
  public void run(String... args) throws Exception {
    exRateService.publishExRates();
  }
}
