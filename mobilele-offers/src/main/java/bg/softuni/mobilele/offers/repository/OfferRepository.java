package bg.softuni.mobilele.offers.repository;

import bg.softuni.mobilele.offers.model.entity.OfferEntity;
import java.time.Instant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

  @Transactional
  @Modifying
  @Query("DELETE FROM OfferEntity o WHERE o.created < :olderThan")
  void deleteOldOffers(Instant olderThan);

}
