package osfix.ag.crm.service.impl.dispatcher.rigging;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.dispatcher.rigging.Stamp;
import osfix.ag.crm.domain.dispatcher.rigging.parts.StampPart;
import osfix.ag.crm.repo.rigging.StampRepo;
import osfix.ag.crm.service.StampService;

import java.util.List;
@Service
public class StampServiceImpl implements StampService {
    private StampRepo stampRepo;

    public StampServiceImpl(StampRepo stampRepo) {
        this.stampRepo = stampRepo;
    }

    @Override
    public List<Stamp> findAll() {
        return stampRepo.findAll();
    }

    @Override
    public Stamp findById(Long id) {
        return stampRepo.findById(id).orElse(null);
    }

    @Override
    public Stamp update(Long id, Stamp stamp) {
        Stamp stampFromDb = stampRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(stamp, stampFromDb, "id");
        return stampRepo.save(stampFromDb);
    }

    @Override
    public Stamp save(Stamp stamp) {
        return stampRepo.save(stamp);
    }

    @Override
    public void delete(Long id) {
        stampRepo.deleteById(id);
    }

    @Override
    public Stamp addParts(List<StampPart> stampParts) {
        return null;
    }
}
