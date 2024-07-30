package bg.softuni.mobilele.service.impl;

import static bg.softuni.mobilele.config.KafkaConfig.EXCHANGE_RATE_TOPIC;

import bg.softuni.mobilele.model.dto.ExRateDTO;
import bg.softuni.mobilele.service.KafkaPublicationService;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublicationServiceImpl implements KafkaPublicationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaPublicationServiceImpl.class);
  private final KafkaTemplate<String, Object> kafkaTemplate;

  public KafkaPublicationServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void publishExchangeRate(ExRateDTO exchangeRateDTO) {
    kafkaTemplate.
        send(EXCHANGE_RATE_TOPIC, UUID.randomUUID().toString(), exchangeRateDTO).
        whenComplete(
            (res, ex) -> {
              if (ex == null) {
                LOGGER.info(
                    "Kafka message successfully send to topic {}/partition {}/ offset {}. Key = {}.",
                    res.getRecordMetadata().topic(),
                    res.getRecordMetadata().partition(),
                    res.getRecordMetadata().offset(),
                    res.getProducerRecord().key()
                );
              } else {
                LOGGER.error("Problem with the publication to kafka.", ex);
              }
            }

        );
  }

}
