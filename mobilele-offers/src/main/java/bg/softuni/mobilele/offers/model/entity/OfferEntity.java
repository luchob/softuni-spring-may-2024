package bg.softuni.mobilele.offers.model.entity;

import static org.hibernate.type.SqlTypes.VARCHAR;

import bg.softuni.mobilele.offers.model.enums.EngineTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import org.hibernate.annotations.JdbcTypeCode;

@Entity
@Table(name = "offers")
public class OfferEntity  {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  private String description;

  @Positive
  private Integer mileage;

  @Positive
  private int price;

  @Enumerated(EnumType.STRING)
  private EngineTypeEnum engine;

  @JdbcTypeCode(VARCHAR)
  private UUID uuid;

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

  public int getPrice() {
    return price;
  }

  public OfferEntity setPrice(int price) {
    this.price = price;
    return this;
  }

  public Long getId() {
    return id;
  }

  public OfferEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return uuid;
  }

  public OfferEntity setUuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }
}
