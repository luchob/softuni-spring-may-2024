package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.model.entity.ExRateEntity;
import bg.softuni.mobilele.model.entity.OfferEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExRateRepository extends JpaRepository<ExRateEntity, Long> {
  Optional<ExRateEntity> findByCurrency(String currency);
}

