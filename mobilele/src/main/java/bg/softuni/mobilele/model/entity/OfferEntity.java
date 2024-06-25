package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.enums.EngineTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {

  private String description;

  private BigDecimal price;

  private Integer mileage;

  @ManyToOne
  private UserEntity seller;

  @Enumerated(EnumType.STRING)
  private EngineTypeEnum engine;

  public Integer getMileage() {
    return mileage;
  }

  public OfferEntity setMileage(Integer mileage) {
    this.mileage = mileage;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public OfferEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  public EngineTypeEnum getEngine() {
    return engine;
  }

  public OfferEntity setEngine(EngineTypeEnum engine) {
    this.engine = engine;
    return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public OfferEntity setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public UserEntity getSeller() {
    return seller;
  }

  public OfferEntity setSeller(UserEntity seller) {
    this.seller = seller;
    return this;
  }

  public String getSellerFullName() {
    StringBuilder sb = new StringBuilder();
    if (seller.getFirstName() != null) {
      sb.append(seller.getFirstName());
    }
    if (seller.getLastName() != null) {
      if (!sb.isEmpty()) {
        sb.append(" ");
      }
      sb.append(seller.getLastName());
    }
    return sb.toString();
  }
}
