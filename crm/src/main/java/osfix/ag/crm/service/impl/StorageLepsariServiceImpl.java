package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.StorageLepsari;
import osfix.ag.crm.repo.StorageLepsariRepo;
import osfix.ag.crm.service.StorageLepsariService;

import java.util.List;

@Service
public class StorageLepsariServiceImpl implements StorageLepsariService {
    private StorageLepsariRepo storageLepsariRepo;

    public StorageLepsariServiceImpl(StorageLepsariRepo storageLepsariRepo) {
        this.storageLepsariRepo = storageLepsariRepo;
    }

    @Override
    public List<StorageLepsari> findAll() {
        return storageLepsariRepo.findAll();
    }

    @Override
    public StorageLepsari findById(Long id) {
        return storageLepsariRepo.findById(id).orElse(null);
    }

    @Override
    public StorageLepsari save(StorageLepsari storageLepsari) {
        return storageLepsariRepo.save(storageLepsari);
    }

    @Override
    public StorageLepsari update(Long id, StorageLepsari storageLepsari) {
        StorageLepsari storageLepsariFromDb = storageLepsariRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(storageLepsari, storageLepsariFromDb, "id");
        return storageLepsariRepo.save(storageLepsariFromDb);
    }

    @Override
    public void delete(Long id) {
        storageLepsariRepo.deleteById(id);
    }
}
