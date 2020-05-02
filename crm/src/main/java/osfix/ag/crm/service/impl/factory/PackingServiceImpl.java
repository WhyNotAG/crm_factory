package osfix.ag.crm.service.impl.factory;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.factory.Packing;
import osfix.ag.crm.repo.factory.EquipmentRepo;
import osfix.ag.crm.repo.factory.PackingRepo;
import osfix.ag.crm.service.PackingService;
import osfix.ag.crm.service.dto.factory.PackingDTO;

import java.util.List;
@Service
public class PackingServiceImpl implements PackingService {
    PackingRepo packingRepo;
    public PackingServiceImpl(PackingRepo packingRepo) {
        this.packingRepo = packingRepo;
    }

    @Override
    public List<Packing> findAll() {
        return packingRepo.findAll();
    }

    @Override
    public Packing findById(Long id) {
        return packingRepo.findById(id).orElse(null);
    }

    @Override
    public Packing update(Long id, Packing packing) {
        Packing packingFromDB = packingRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(packing, packingFromDB, "id");
        return packingRepo.save(packingFromDB);
    }

    @Override
    public Packing save(Packing packing) {
        return packingRepo.save(packing);
    }

    @Override
    public void delete(Long id) {
        packingRepo.deleteById(id);
    }
}
