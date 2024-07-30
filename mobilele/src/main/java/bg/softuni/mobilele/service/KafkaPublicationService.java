package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.ExRateDTO;

public interface KafkaPublicationService {
  void publishExchangeRate(ExRateDTO exRateDTO);
}
