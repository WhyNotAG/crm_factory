package osfix.ag.crm.service.impl.dispatcher.rigging.part;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.dispatcher.rigging.parts.StampPart;
import osfix.ag.crm.repo.rigging.part.StampPartRepo;
import osfix.ag.crm.service.StampPartService;

import java.util.List;

@Service
public class StampPartServiceImpl implements StampPartService {
    private StampPartRepo stampPartRepo;

    public StampPartServiceImpl(StampPartRepo stampPartRepo) {
        this.stampPartRepo = stampPartRepo;
    }

    @Override
    public List<StampPart> findAll() {
        return stampPartRepo.findAll();
    }

    @Override
    public StampPart findById(Long id) {
        return stampPartRepo.findById(id).orElse(null);
    }

    @Override
    public StampPart update(Long id, StampPart stampPart) {
        StampPart stampPartFromDb = stampPartRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(stampPart, stampPartFromDb, "id");
        return stampPartFromDb;
    }

    @Override
    public StampPart save(StampPart stampPart) {
        return stampPartRepo.save(stampPart);
    }

    @Override
    public void delete(Long id) {
        stampPartRepo.deleteById(id);
    }
}
