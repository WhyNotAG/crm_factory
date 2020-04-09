package osfix.ag.crm.repo.factory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.factory.Equipment;

import java.util.List;

@Repository
public interface EquipmentRepo extends JpaRepository<Equipment, Long> {
    List<Equipment> findAllByFactoryName(String name);
}
