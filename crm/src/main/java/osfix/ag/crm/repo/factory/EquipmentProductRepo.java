package osfix.ag.crm.repo.factory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.factory.EquipmentProduct;

@Repository
public interface EquipmentProductRepo extends JpaRepository<EquipmentProduct, Long> {
}
