package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Storage;
import osfix.ag.crm.repo.StorageRepo;
import osfix.ag.crm.service.StorageService;

import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {
    private StorageRepo storageRepo;

    public StorageServiceImpl(StorageRepo storageRepo) { this.storageRepo = storageRepo; }

    @Override
    public List<Storage> findAll() { return storageRepo.findAll(); }

    @Override
    public Storage findById(Long id) { return storageRepo.findById(id).orElse(null); }

    @Override
    public Storage save(Storage storage) { return storageRepo.save(storage); }

    @Override
    public Storage update(Long id, Storage storage) {
        Storage storageFromDb = storageRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(storage,storageFromDb,"id");
        return storageRepo.save(storageFromDb);
    }

    @Override
    public void delete(Long id) { storageRepo.deleteById(id); }
}
