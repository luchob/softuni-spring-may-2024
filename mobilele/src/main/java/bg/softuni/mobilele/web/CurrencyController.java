package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.ConversionResultDTO;
import bg.softuni.mobilele.service.ExRateService;
import bg.softuni.mobilele.service.exception.ApiObjectNotFoundException;
import bg.softuni.mobilele.web.aop.WarnIfExecutionExceeds;
import java.math.BigDecimal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

  private final ExRateService exRateService;

  public CurrencyController(ExRateService exRateService) {
    this.exRateService = exRateService;
  }


  @WarnIfExecutionExceeds(timeInMillis = 1000)
  @GetMapping("/api/convert")
  public ResponseEntity<ConversionResultDTO> convert(
      @RequestParam("from") String from,
      @RequestParam("to") String to,
      @RequestParam("amount") BigDecimal amount
  ) {
    BigDecimal result = exRateService.convert(from, to, amount);

    return ResponseEntity.ok(new ConversionResultDTO(
        from,
        to,
        amount,
        result
    ));
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ApiObjectNotFoundException.class)
  @ResponseBody
  public NotFoundErrorInfo handleApiObjectNotFoundException(ApiObjectNotFoundException apiObjectNotFoundException) {
    return new NotFoundErrorInfo("NOT FOUND", apiObjectNotFoundException.getId());
  }


  public record NotFoundErrorInfo(String code, Object id) {

  }
}
