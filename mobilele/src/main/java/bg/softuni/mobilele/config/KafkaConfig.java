package bg.softuni.mobilele.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

  public static final String EX_RATES_TOPIC = "ex-rates";

  @Bean
  public NewTopic exRatesTopic() {
    return TopicBuilder.name(EX_RATES_TOPIC)
        .partitions(2)
        .compact()
        .build();
  }

}
