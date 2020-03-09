package osfix.ag.crm.service;

import osfix.ag.crm.domain.manager.LegalEntity;

import java.util.List;

public interface LegalEntityService {
    List<LegalEntity> findAll();
    LegalEntity findById(Long id);
    LegalEntity update(Long id, LegalEntity legalEntity);
    LegalEntity save(LegalEntity legalEntity);
    void delete(Long id);
}
