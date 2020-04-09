package osfix.ag.crm.service;

import osfix.ag.crm.domain.factory.Equipment;

import java.util.List;

public interface EquipmentService {
    List<Equipment> findAll();
    List<Equipment> findAllByFactoryName(String name);
    Equipment findById(Long id);
    Equipment update(Long id, Equipment equipment);
    Equipment save(Equipment equipment);
    void delete(Long id);
}
