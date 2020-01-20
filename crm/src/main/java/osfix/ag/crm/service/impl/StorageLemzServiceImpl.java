package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.StorageLemz;
import osfix.ag.crm.repo.StorageLemzRepo;
import osfix.ag.crm.service.StorageLemzService;

import java.util.List;

@Service
public class StorageLemzServiceImpl implements StorageLemzService {
    private StorageLemzRepo storageLemzRepo;

    public StorageLemzServiceImpl(StorageLemzRepo storageLemzRepo) { this.storageLemzRepo = storageLemzRepo; }

    @Override
    public List<StorageLemz> findAll() { return storageLemzRepo.findAll(); }

    @Override
    public StorageLemz findById(Long id) { return storageLemzRepo.findById(id).orElse(null); }

    @Override
    public StorageLemz save(StorageLemz storageLemz) { return storageLemzRepo.save(storageLemz); }

    @Override
    public StorageLemz update(Long id, StorageLemz storageLemz) {
        StorageLemz storageLemzFromDb = storageLemzRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(storageLemz, storageLemzFromDb,"id");
        return storageLemzRepo.save(storageLemzFromDb);
    }

    @Override
    public void delete(Long id) { storageLemzRepo.deleteById(id); }
}
