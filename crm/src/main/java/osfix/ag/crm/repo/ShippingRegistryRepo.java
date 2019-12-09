package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.dispatcher.ShippingRegistry;

@Repository
public interface ShippingRegistryRepo extends JpaRepository<ShippingRegistry, Long> {
}
