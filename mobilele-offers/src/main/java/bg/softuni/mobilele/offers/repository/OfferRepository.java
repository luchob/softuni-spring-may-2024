package bg.softuni.mobilele.offers.repository;

import bg.softuni.mobilele.offers.model.entity.OfferEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

}
