package osfix.ag.crm.service;

import osfix.ag.crm.domain.Storage;

import java.util.List;

public interface StorageService {
    List<Storage> findAll();
    Storage findById(Long id);
    Storage save(Storage storage);
    Storage update(Long id, Storage storage);
    void delete(Long id);
}
