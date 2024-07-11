package bg.softuni.mobilele.service.impl;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

import bg.softuni.mobilele.config.ForexApiConfig;
import bg.softuni.mobilele.model.dto.ExRatesDTO;
import bg.softuni.mobilele.service.ExRateService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import java.math.BigDecimal;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableWireMock(
    @ConfigureWireMock(name = "exchange-rate-service")
)
public class ExRateServiceImplIT {

  @InjectWireMock("exchange-rate-service")
  private WireMockServer wireMockServer;

  @Autowired
  private ExRateService exRateService;

  @Autowired
  private ForexApiConfig forexApiConfig;

  @BeforeEach
  void setUp() {
    forexApiConfig.setUrl(wireMockServer.baseUrl() + "/test-currencies");
  }

  @Test
  void testFetchExchangeRates() {
    wireMockServer.stubFor(get("/test-currencies").willReturn(
        aResponse()
            .withHeader("Content-Type", "application/json")
            .withBody(
                """
                  {
                    "base": "USD",
                    "rates" : {
                      "BGN": 1.86,
                      "EUR": 0.91
                    }
                  }
                """
            )
        )
    );

    ExRatesDTO exRatesDTO = exRateService.fetchExRates();

    Assertions.assertEquals("USD", exRatesDTO.base());
    Assertions.assertEquals(2, exRatesDTO.rates().size());
    Assertions.assertEquals(new BigDecimal("1.86"), exRatesDTO.rates().get("BGN"));
    Assertions.assertEquals(new BigDecimal("0.91"), exRatesDTO.rates().get("EUR"));
  }

}
