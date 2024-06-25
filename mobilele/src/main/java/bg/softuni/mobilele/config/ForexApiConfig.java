package bg.softuni.mobilele.config;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotEmpty;
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
  public void checkConfiguration() {

    verifyNotNullOrEmpty("key", key);
    verifyNotNullOrEmpty("base", base);
    verifyNotNullOrEmpty("url", url);

    if (!"USD".equals(base)) {
      throw new IllegalStateException("Sorry, but the free API does not support base, "
          + "currencies different than USD.");
    }


  }

  private static void verifyNotNullOrEmpty(String name, String value) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("Property " + name + " cannot be empty.");
    }
  }
}
