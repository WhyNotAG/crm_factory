package osfix.ag.crm.service.impl.dispatcher.rigging.part;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Log;
import osfix.ag.crm.domain.dispatcher.rigging.parts.PressPart;
import osfix.ag.crm.repo.LogRepo;
import osfix.ag.crm.repo.rigging.part.PressPartRepo;
import osfix.ag.crm.service.PressPartService;

import java.util.List;

@Service
public class PressPartServiceImpl implements PressPartService {
    private PressPartRepo pressPartRepo;
    private LogRepo logRepo;

    public PressPartServiceImpl(PressPartRepo pressPartRepo, LogRepo logRepo) {
        this.pressPartRepo = pressPartRepo;
        this.logRepo = logRepo;
    }

    @Override
    public List<PressPart> findAll() {
        return pressPartRepo.findAll();
    }

    @Override
    public PressPart findById(Long id) {
        return pressPartRepo.findById(id).orElse(null);
    }

    @Override
    public PressPart update(Long id, PressPart pressPart) {
        PressPart pressPartFromDb = pressPartRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(pressPart, pressPartFromDb, "id");
        loging("Изменение детали", "Изменение в оснастке №" + pressPartFromDb.getPress().getId(), "rigging", pressPartFromDb.getId());
        return pressPartRepo.save(pressPartFromDb);
    }

    @Override
    public PressPart save(PressPart pressPart) {
        pressPartRepo.save(pressPart);
        loging("Добавление детали", "Добавление в оснастке №" + pressPart.getPress().getId(), "rigging", pressPart.getId());
        return pressPart;
    }

    @Override
    public void delete(Long id) {
        pressPartRepo.deleteById(id);
        loging("Удаление детали", "Удаление детали", "rigging", id);
    }

    @Override
    public PressPart changeColor(Long id, String color) {
        PressPart pressPart = pressPartRepo.findById(id).orElse(null);
        pressPart.setColor(color);
        return pressPartRepo.save(pressPart);
    }

    public void loging(String actionShort, String action, String type, Long id) {
        Log log = new Log();
        log.setAction(actionShort);
        log.setDate(java.util.Calendar.getInstance().getTime());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.setAuthor(authentication.getName());
        log.setDescription( action + " детали №" + id + " в \"Пресс\"" );
        log.setType(type);
        log.setElementId(id);
        logRepo.save(log);
    }
}
