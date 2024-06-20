package bg.softuni.mobilele.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ex_rates")
public class ExRateEntity extends BaseEntity {

  private String currency;
  private BigDecimal rate;
  public String getCurrency() {
    return currency;
  }

  public ExRateEntity setCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public ExRateEntity setRate(BigDecimal rate) {
    this.rate = rate;
    return this;
  }
}
