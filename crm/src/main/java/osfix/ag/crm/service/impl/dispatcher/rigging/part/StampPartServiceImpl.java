package osfix.ag.crm.service.impl.dispatcher.rigging.part;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Log;
import osfix.ag.crm.domain.dispatcher.rigging.parts.StampPart;
import osfix.ag.crm.repo.LogRepo;
import osfix.ag.crm.repo.rigging.part.StampPartRepo;
import osfix.ag.crm.service.StampPartService;

import java.util.List;

@Service
public class StampPartServiceImpl implements StampPartService {
    private StampPartRepo stampPartRepo;
    private LogRepo logRepo;

    public StampPartServiceImpl(StampPartRepo stampPartRepo, LogRepo logRepo) {
        this.stampPartRepo = stampPartRepo;
        this.logRepo = logRepo;
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
        loging("Изменение детали", "Изменение в оснастке №" + stampPartFromDb.getStamp().getId(), "riggingPart", stampPartFromDb.getId());
        return stampPartRepo.save(stampPartFromDb);
    }

    @Override
    public StampPart save(StampPart stampPart) {
        stampPartRepo.save(stampPart);
        loging("Добавление детали", "Добавление в оснастке №" + stampPart.getStamp().getId(), "riggingPart", stampPart.getId());
        return stampPart;
    }

    @Override
    public void delete(Long id) {
        stampPartRepo.deleteById(id);
        loging("Удаление детали", "Удаление детали", "riggingPart", id);
    }

    @Override
    public StampPart changeColor(Long id, String color) {
        StampPart stampPart = stampPartRepo.findById(id).orElse(null);
        stampPart.setColor(color);
        return stampPartRepo.save(stampPart);
    }

    public void loging(String actionShort, String action, String type, Long id) {
        Log log = new Log();
        log.setAction(actionShort);
        log.setDate(java.util.Calendar.getInstance().getTime());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.setAuthor(authentication.getName());
        log.setDescription( action + " детали №" + id + " в \"Штамп\"" );
        log.setType(type);
        log.setElementId(id);
        logRepo.save(log);
    }
}
