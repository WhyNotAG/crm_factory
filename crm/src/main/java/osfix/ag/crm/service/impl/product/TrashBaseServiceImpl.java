package osfix.ag.crm.service.impl.product;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.TrashBase;
import osfix.ag.crm.repo.TrashBaseRepo;
import osfix.ag.crm.service.TrashBaseService;
import java.util.List;

@Service
public class TrashBaseServiceImpl implements TrashBaseService {
    TrashBaseRepo trashBaseRepo;

    public TrashBaseServiceImpl(TrashBaseRepo trashBaseRepo) {
        this.trashBaseRepo = trashBaseRepo;
    }

    @Override
    public List<TrashBase> findAll() {
        return trashBaseRepo.findAll();
    }

    @Override
    public TrashBase findById(Long id) {
        return trashBaseRepo.findById(id).orElse(null);
    }

    @Override
    public TrashBase save(TrashBase trashBase) {
        return trashBaseRepo.save(trashBase);
    }

    @Override
    public TrashBase update(Long id, TrashBase trashBase) {
        TrashBase trashBaseFromDb = trashBaseRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(trashBase, trashBaseFromDb, "id");
        return trashBaseRepo.save(trashBaseFromDb);
    }

    @Override
    public void delete(Long id) {
        trashBaseRepo.deleteById(id);
    }
}
