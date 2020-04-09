package osfix.ag.crm.service;

import osfix.ag.crm.domain.factory.EquipmentProduct;

import java.util.List;

public interface EquipmentProductsService {
    List<EquipmentProduct> findAll();
    EquipmentProduct findById(Long id);
    EquipmentProduct update(Long id, EquipmentProduct equipmentProduct);
    EquipmentProduct save(EquipmentProduct equipmentProduct);
    void delete(Long id);
}
