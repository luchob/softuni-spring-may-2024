package bg.softuni.mobilele.web;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import bg.softuni.mobilele.service.ExRateService;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ExRateService exRateService;


  @Test
  public void testConvert() throws Exception {
    String from = "USD";
    String to = "EUR";
    BigDecimal amount = new BigDecimal("100");
    BigDecimal expectedConversionResult = new BigDecimal("85"); // Expected result

    // Mocking the exRateService
    when(exRateService.convert(eq(from), eq(to), eq(amount))).thenReturn(expectedConversionResult);

    mockMvc.perform(get("/api/convert")
            .param("from", from)
            .param("to", to)
            .param("amount", amount.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.from").value(from))
        .andExpect(jsonPath("$.to").value(to))
        .andExpect(jsonPath("$.amount").value(amount))
        .andExpect(jsonPath("$.result").value(expectedConversionResult));
  }
}
