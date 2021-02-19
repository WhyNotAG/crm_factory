package osfix.ag.crm.service.impl.manager;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Log;
import osfix.ag.crm.domain.manager.History;
import osfix.ag.crm.repo.LogRepo;
import osfix.ag.crm.repo.manager.HistoryRepo;
import osfix.ag.crm.service.HistoryService;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    private HistoryRepo historyRepo;
    private LogRepo logRepo;

    public HistoryServiceImpl(HistoryRepo historyRepo, LogRepo logRepo) {
        this.historyRepo = historyRepo;
        this.logRepo = logRepo;
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
        loging("Изменение", "Изменение записи", "clients", historyFromDb.getId(), historyFromDb.getClient().getId());
        return historyRepo.save(historyFromDb);
    }

    @Override
    public History save(History history) {
        historyRepo.save(history);
        loging("Создание", "Создание записи", "clients", history.getId(), history.getClient().getId());
        return history;
    }

    @Override
    public void delete(Long id) {
        historyRepo.deleteById(id);
    }

    public void loging(String actionShort, String action, String type, Long id, Long clientId) {
        Log log = new Log();
        log.setAction(actionShort);
        log.setDate(java.util.Calendar.getInstance().getTime());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.setAuthor(authentication.getName());
        log.setDescription( action + " в истории работы №" + id + " клиента №" + clientId);
        log.setType(type);
        logRepo.save(log);
    }
}
