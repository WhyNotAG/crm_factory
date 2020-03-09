package osfix.ag.crm.service.impl.manager;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.manager.LegalEntity;
import osfix.ag.crm.repo.manager.LegalEntityRepo;
import osfix.ag.crm.service.LegalEntityService;

import java.util.List;

@Service
public class LegalEntityServiceImpl implements LegalEntityService {
    private LegalEntityRepo legalEntityRepo;

    public LegalEntityServiceImpl(LegalEntityRepo legalEntityRepo) {
        this.legalEntityRepo = legalEntityRepo;
    }

    @Override
    public List<LegalEntity> findAll() {
        return legalEntityRepo.findAll();
    }

    @Override
    public LegalEntity findById(Long id) {
        return legalEntityRepo.findById(id).orElse(null);
    }

    @Override
    public LegalEntity update(Long id, LegalEntity legalEntity) {
        LegalEntity legalEntityFromDb = legalEntityRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(legalEntity, legalEntityFromDb, "id");
        return legalEntityRepo.save(legalEntityFromDb);
    }

    @Override
    public LegalEntity save(LegalEntity legalEntity) {
        return legalEntityRepo.save(legalEntity);
    }

    @Override
    public void delete(Long id) {
        legalEntityRepo.deleteById(id);
    }
}
