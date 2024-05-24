package eu.balev.virtual.server.config;

import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

  @Bean
  public RestClient restClient() {
    return RestClient.builder()
        .baseUrl("http://localhost:9001")
        .defaultHeaders(httpHeaders -> httpHeaders.set("Content-Type", "application/json"))
        .build();
  }
}
