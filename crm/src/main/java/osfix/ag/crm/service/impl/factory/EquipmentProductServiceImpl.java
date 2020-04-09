package osfix.ag.crm.service.impl.factory;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.factory.EquipmentProduct;
import osfix.ag.crm.repo.factory.EquipmentProductRepo;
import osfix.ag.crm.service.EquipmentProductsService;

import java.util.List;

@Service
public class EquipmentProductServiceImpl implements EquipmentProductsService {
    private EquipmentProductRepo equipmentProductRepo;

    public EquipmentProductServiceImpl(EquipmentProductRepo equipmentProductRepo) {
        this.equipmentProductRepo = equipmentProductRepo;
    }

    @Override
    public List<EquipmentProduct> findAll() {
        return equipmentProductRepo.findAll();
    }

    @Override
    public EquipmentProduct findById(Long id) {
        return equipmentProductRepo.findById(id).orElse(null);
    }

    @Override
    public EquipmentProduct update(Long id, EquipmentProduct equipmentProduct) {
        EquipmentProduct equipmentProductFromDB = equipmentProductRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(equipmentProduct, equipmentProductFromDB, "id");
        return equipmentProductRepo.save(equipmentProductFromDB);
    }

    @Override
    public EquipmentProduct save(EquipmentProduct equipmentProduct) {
        return equipmentProductRepo.save(equipmentProduct);
    }

    @Override
    public void delete(Long id) {
        equipmentProductRepo.deleteById(id);
    }
}
