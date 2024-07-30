package bg.softuni.mobilele.web;

import bg.softuni.mobilele.service.ExRateService;
import bg.softuni.mobilele.service.KafkaPublicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakePublicationController {

  private final ExRateService exRateService;

  public FakePublicationController(ExRateService exRateService) {
    this.exRateService = exRateService;
  }

  @GetMapping("/publish")
  public String publish() {

    exRateService.publishExchangeRatesKafka();

    return "OK";
  }

}
