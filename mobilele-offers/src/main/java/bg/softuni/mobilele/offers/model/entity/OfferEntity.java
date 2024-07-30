package bg.softuni.mobilele.offers.model.entity;

import bg.softuni.mobilele.offers.model.enums.EngineTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.Instant;

@Entity
@Table(name = "offers")
public class OfferEntity  {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  @Column(columnDefinition = "TEXT")//MYSQL Specific!!!!!, demonstrates test containers
  private String description;
  @Positive
  private Integer mileage;

  @Positive
  private int price;

  @Enumerated(EnumType.STRING)
  private EngineTypeEnum engine;
  @NotNull
  @Column
  private Instant created = Instant.now();

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

  public Instant getCreated() {
    return created;
  }

  public OfferEntity setCreated(Instant created) {
    this.created = created;
    return this;
  }
}
