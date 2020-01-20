package osfix.ag.crm.service;

import osfix.ag.crm.domain.StorageLemz;

import java.util.List;

public interface StorageLemzService {
    List<StorageLemz> findAll();
    StorageLemz findById(Long id);
    StorageLemz save(StorageLemz storageLemz);
    StorageLemz update(Long id, StorageLemz storageLemz);
    void delete(Long id);
}
