package bg.softuni.mobilele.offers.service.impl;

import bg.softuni.mobilele.offers.service.MonitoringService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class MonitoringServiceImpl implements MonitoringService {

  private final Counter offerSearchCounter;

  public MonitoringServiceImpl(MeterRegistry meterRegistry) {

    offerSearchCounter = Counter
        .builder("offer.searches")
        .description("How many times offers were searched.")
        .register(meterRegistry);
  }

  @Override
  public void increaseOfferSearches() {
    offerSearchCounter.increment();
  }
}
