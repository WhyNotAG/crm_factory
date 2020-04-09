package osfix.ag.crm.service.impl.factory;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.factory.Equipment;
import osfix.ag.crm.repo.factory.EquipmentRepo;
import osfix.ag.crm.service.EquipmentService;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    private EquipmentRepo equipmentRepo;

    public EquipmentServiceImpl(EquipmentRepo equipmentRepo) {
        this.equipmentRepo = equipmentRepo;
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentRepo.findAll();
    }

    @Override
    public List<Equipment> findAllByFactoryName(String name) {
        return equipmentRepo.findAllByFactoryName(name);
    }

    @Override
    public Equipment findById(Long id) {
        return equipmentRepo.findById(id).orElse(null);
    }

    @Override
    public Equipment update(Long id, Equipment equipment) {
        Equipment equipmentFromBD = equipmentRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(equipment, equipmentFromBD, "id");
        return equipmentRepo.save(equipmentFromBD);
    }

    @Override
    public Equipment save(Equipment equipment) {
        return equipmentRepo.save(equipment);
    }

    @Override
    public void delete(Long id) {
        equipmentRepo.deleteById(id);
    }
}
