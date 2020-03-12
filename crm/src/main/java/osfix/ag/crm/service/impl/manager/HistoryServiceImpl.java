package osfix.ag.crm.service.impl.manager;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.manager.History;
import osfix.ag.crm.repo.manager.HistoryRepo;
import osfix.ag.crm.service.HistoryService;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    private HistoryRepo historyRepo;

    public HistoryServiceImpl(HistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }

    @Override
    public List<History> findAll() {
        return historyRepo.findAll();
    }

    @Override
    public History findById(Long id) {
        return historyRepo.findById(id).orElse(null);
    }

    @Override
    public History update(Long id, History history) {
        History historyFromDb = historyRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(history, historyFromDb, "id");
        return historyRepo.save(historyFromDb);
    }

    @Override
    public History save(History history) {
        return historyRepo.save(history);
    }

    @Override
    public void delete(Long id) {
        historyRepo.deleteById(id);
    }
}
