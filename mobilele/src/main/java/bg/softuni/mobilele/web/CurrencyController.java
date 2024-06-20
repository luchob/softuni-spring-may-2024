package bg.softuni.mobilele.web;

import bg.softuni.mobilele.service.ExRateService;
import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

  private final ExRateService exRateService;

  public CurrencyController(ExRateService exRateService) {
    this.exRateService = exRateService;
  }

  @GetMapping("/api/convert")
  public ResponseEntity<Object> convert(@RequestParam("from") String from,
                                            @RequestParam("to") String to,
                                            @RequestParam("amount") BigDecimal amount) {

    return ResponseEntity.ok(new Object() {
                               public BigDecimal getResult() {
                                 return exRateService.convert(from, to, amount);
                               }
                             }
    );
  }
}
