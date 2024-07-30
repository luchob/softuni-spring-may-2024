package bg.softuni.kafka.consumer;

import bg.softuni.mobilele.model.dto.ExRateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ExRatesConsumer {

  public static final String EX_RATES_TOPIC = "ex-rates";

  private static final Logger LOGGER = LoggerFactory.getLogger(ExRatesConsumer.class);

  @KafkaListener(
      topics = EX_RATES_TOPIC,
      groupId = "ex-rates-cg-3")
  public void listenGroupFoo(ExRateDTO exRateDTO) {

    LOGGER.info("Consumed ex rate {}/{}",
        exRateDTO.currency(),
        exRateDTO.rate()
    );
  }

}
