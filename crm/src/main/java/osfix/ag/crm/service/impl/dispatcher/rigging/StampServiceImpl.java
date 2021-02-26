package osfix.ag.crm.service.impl.dispatcher.rigging;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Log;
import osfix.ag.crm.domain.dispatcher.rigging.Stamp;
import osfix.ag.crm.domain.dispatcher.rigging.parts.StampPart;
import osfix.ag.crm.repo.LogRepo;
import osfix.ag.crm.repo.rigging.StampRepo;
import osfix.ag.crm.service.StampService;

import java.util.List;
@Service
public class StampServiceImpl implements StampService {
    private StampRepo stampRepo;
    private LogRepo logRepo;

    public StampServiceImpl(StampRepo stampRepo, LogRepo logRepo) {
        this.stampRepo = stampRepo;
        this.logRepo = logRepo;
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
        loging("Изменение", "Изменение", "rigging", stampFromDb.getId(), stampFromDb.getStatus());
        return stampRepo.save(stampFromDb);
    }

    @Override
    public Stamp save(Stamp stamp) {
        stampRepo.save(stamp);
        loging("Создание", "Создание", "rigging", stamp.getId(), stamp.getStatus());
        return stamp;
    }

    @Override
    public void delete(Long id) {
        Stamp stamp = stampRepo.findById(id).orElse(null);
        stampRepo.deleteById(id);
        loging("Удаление", "Удаление", "rigging", id, stamp.getStatus());
    }

    @Override
    public Stamp addParts(List<StampPart> stampParts) {
        return null;
    }

    @Override
    public Stamp changeColor(Long id, String color) {
        Stamp stamp = stampRepo.findById(id).orElse(null);
        stamp.setColor(color);
        return stampRepo.save(stamp);
    }

    @Override
    public List<Stamp> findAllByStatus(String status) {
        return stampRepo.findAllByStatus(status);
    }

    public void loging(String actionShort, String action, String type, Long id, String elementType) {
        Log log = new Log();
        log.setAction(actionShort);
        log.setDate(java.util.Calendar.getInstance().getTime());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.setAuthor(authentication.getName());
        String riggingType = "";
        if (elementType.equals("pressForm")) riggingType = "Пресс";
        if (elementType.equals("stamp")) riggingType = "Штамп";
        if (elementType.equals("bench")) riggingType = "Станок";
        if (elementType.equals("detail")) riggingType = "Деталь";
        log.setDescription( action + " оснастки №" + id + " в \"" + riggingType + "\"" );
        log.setType(type);
        log.setElementId(id);
        logRepo.save(log);
    }
}
