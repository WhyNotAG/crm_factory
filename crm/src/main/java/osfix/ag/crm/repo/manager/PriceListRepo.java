package osfix.ag.crm.repo.manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.manager.Prices;

@Repository
public interface PriceListRepo extends JpaRepository<Prices, Long> {
}
