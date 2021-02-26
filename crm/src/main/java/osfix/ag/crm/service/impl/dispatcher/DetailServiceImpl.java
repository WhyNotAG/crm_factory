package osfix.ag.crm.service.impl.dispatcher;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Log;
import osfix.ag.crm.domain.dispatcher.Detail;
import osfix.ag.crm.repo.DetailRepo;
import osfix.ag.crm.repo.LogRepo;
import osfix.ag.crm.service.DetailService;

import java.util.List;

@Service
public class DetailServiceImpl implements DetailService {
    private DetailRepo detailRepo;
    private LogRepo logRepo;

    public DetailServiceImpl(DetailRepo detailRepo, LogRepo logRepo) {
        this.detailRepo = detailRepo;
        this.logRepo = logRepo;
    }

    @Override
    public List<Detail> findAll() { return detailRepo.findAll(); }

    @Override
    public Detail findById(Long id) { return detailRepo.findById(id).orElse(null); }

    @Override
    public Detail update(Long id, Detail detail) {
        Detail detailFromDb = detailRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(detail,detailFromDb, "id");
        loging("Изменение", "Изменение", "rigging", detailFromDb.getId());
        return detailRepo.save(detailFromDb);
    }

    @Override
    public Detail save(Detail detail) {
        detailRepo.save(detail);
        loging("Создание", "Создание", "rigging", detail.getId());
        return detail;
    }

    @Override
    public void delete(Long id) {
        detailRepo.deleteById(id);
        loging("Удаление", "Удаление", "rigging", id);
    }

    @Override
    public Detail changeColor(Long id, String color) {
        Detail detail = detailRepo.findById(id).orElse(null);
        detail.setColor(color);
        return detailRepo.save(detail);
    }

    public void loging(String actionShort, String action, String type, Long id) {
        Log log = new Log();
        log.setAction(actionShort);
        log.setDate(java.util.Calendar.getInstance().getTime());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.setAuthor(authentication.getName());
        log.setDescription( action + " оснастки №" + id + " в \"Запчасти\"" );
        log.setType(type);
        log.setElementId(id);
        logRepo.save(log);
    }
}
