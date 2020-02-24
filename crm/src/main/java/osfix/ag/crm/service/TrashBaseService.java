package osfix.ag.crm.service;

import osfix.ag.crm.domain.TrashBase;

import java.util.List;

public interface TrashBaseService {
    List<TrashBase> findAll();
    TrashBase findById(Long id);
    TrashBase save(TrashBase trashBase);
    TrashBase update(Long id, TrashBase trashBase);
    void delete(Long id);

}
