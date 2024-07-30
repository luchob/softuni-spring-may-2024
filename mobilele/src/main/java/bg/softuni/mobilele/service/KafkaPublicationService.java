package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.ExRateDTO;

public interface KafkaPublicationService {

  void publishExRate(ExRateDTO exRateDTO);
}
