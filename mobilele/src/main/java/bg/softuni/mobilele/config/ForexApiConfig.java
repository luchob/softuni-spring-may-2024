package bg.softuni.mobilele.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "forex.api")
public class ForexApiConfig {

  private String key;
  private String url;
  private String base;

  public String getKey() {
    return key;
  }

  public ForexApiConfig setKey(String key) {
    this.key = key;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public ForexApiConfig setUrl(String url) {
    this.url = url;
    return this;
  }

  public String getBase() {
    return base;
  }

  public ForexApiConfig setBase(String base) {
    this.base = base;
    return this;
  }

  @PostConstruct
  void check() {
    if (isNullOrEmpty(key, url, base)) {
      throw new IllegalStateException("Forex API configuration is not set correctly!");
    }
  }

  private boolean isNullOrEmpty(String ...values) {
    for (String value : values) {
      if (value == null || value.isBlank()) {
        return true;
      }
    }
    return false;
  }
}
