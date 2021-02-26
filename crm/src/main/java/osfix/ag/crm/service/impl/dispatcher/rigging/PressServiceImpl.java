package osfix.ag.crm.service.impl.dispatcher.rigging;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Log;
import osfix.ag.crm.domain.dispatcher.rigging.Press;
import osfix.ag.crm.domain.dispatcher.rigging.parts.PressPart;
import osfix.ag.crm.repo.LogRepo;
import osfix.ag.crm.repo.rigging.PressRepo;
import osfix.ag.crm.service.PressService;

import java.util.List;
@Service
public class PressServiceImpl implements PressService {
    private PressRepo pressRepo;
    private LogRepo logRepo;

    public PressServiceImpl(PressRepo pressRepo, LogRepo logRepo) {
        this.pressRepo = pressRepo;
        this.logRepo = logRepo;
    }

    @Override
    public List<Press> findAll() {
        return pressRepo.findAll();
    }

    @Override
    public Press findById(Long id) {
        return pressRepo.findById(id).orElse(null);
    }

    @Override
    public Press update(Long id, Press press) {
        Press pressFromDb = pressRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(press, pressFromDb, "id");
        loging("Изменение", "Изменение", "rigging", pressFromDb.getId());
        return pressRepo.save(pressFromDb);
    }

    @Override
    public Press save(Press press) {
        pressRepo.save(press);
        loging("Создание", "Создание", "rigging", press.getId());
        return press;
    }

    @Override
    public void delete(Long id) {
        pressRepo.deleteById(id);
        loging("Удаление", "Удаление", "rigging", id);
    }

    @Override
    public Press addParts(List<PressPart> pressParts) {
        return null;
    }

    @Override
    public Press changeColor(Long id, String color) {
        Press press = pressRepo.findById(id).orElse(null);
        press.setColor(color);
        return pressRepo.save(press);
    }

    public void loging(String actionShort, String action, String type, Long id) {
        Log log = new Log();
        log.setAction(actionShort);
        log.setDate(java.util.Calendar.getInstance().getTime());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.setAuthor(authentication.getName());
        log.setDescription( action + " оснастки №" + id + " в \"Пресс\"" );
        log.setType(type);
        log.setElementId(id);
        logRepo.save(log);
    }
}
