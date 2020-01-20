package osfix.ag.crm.service;

import osfix.ag.crm.domain.StorageLepsari;

import java.util.List;

public interface StorageLepsariService {
    List<StorageLepsari> findAll();
    StorageLepsari findById(Long id);
    StorageLepsari save(StorageLepsari storageLepsari);
    StorageLepsari update(Long id, StorageLepsari storageLepsari);
    void delete(Long id);
}
