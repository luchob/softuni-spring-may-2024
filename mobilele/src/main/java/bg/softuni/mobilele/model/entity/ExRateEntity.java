package bg.softuni.mobilele.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "ex_rates")
public class ExRateEntity extends BaseEntity {

  @NotEmpty
  @Column(unique = true)
  private String currency;

  @Positive
  @NotNull
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

  @Override
  public String toString() {
    return "ExRateEntity{" +
        "currency='" + currency + '\'' +
        ", rate=" + rate +
        '}';
  }
}
