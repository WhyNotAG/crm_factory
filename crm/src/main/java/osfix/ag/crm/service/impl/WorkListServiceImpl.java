package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.WorkList;
import osfix.ag.crm.repo.WorkListRepo;
import osfix.ag.crm.service.WorkListService;

import java.util.List;

@Service
public class WorkListServiceImpl implements WorkListService {
    private WorkListRepo workListRepo;

    public WorkListServiceImpl(WorkListRepo workListRepo) { this.workListRepo = workListRepo; }

    @Override
    public List<WorkList> findAll() { return workListRepo.findAll(); }

    @Override
    public WorkList findById(Long id) { return workListRepo.findById(id).orElse(null); }

    @Override
    public WorkList save(WorkList workList) { return workListRepo.save(workList); }

    @Override
    public WorkList update(Long id, WorkList workList) {
        WorkList workListFromDb = workListRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(workList, workListFromDb, "id");
        return workListRepo.save(workListFromDb);
    }

    @Override
    public void delete(Long id) { workListRepo.deleteById(id); }
}
