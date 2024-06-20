package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.model.entity.ExRateEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExRateRepository extends JpaRepository<ExRateEntity, Long> {

  Optional<ExRateEntity> findByCurrency(String currency);
}
