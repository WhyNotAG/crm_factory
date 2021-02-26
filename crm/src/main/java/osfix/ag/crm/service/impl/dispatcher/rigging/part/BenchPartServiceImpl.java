package osfix.ag.crm.service.impl.dispatcher.rigging.part;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Log;
import osfix.ag.crm.domain.dispatcher.rigging.parts.BenchPart;
import osfix.ag.crm.repo.LogRepo;
import osfix.ag.crm.repo.rigging.part.BenchPartRepo;
import osfix.ag.crm.service.BenchPartService;

import java.util.List;

@Service
public class BenchPartServiceImpl implements BenchPartService {
    private BenchPartRepo benchPartRepo;
    private LogRepo logRepo;

    public BenchPartServiceImpl(BenchPartRepo benchPartRepo, LogRepo logRepo) {
        this.benchPartRepo = benchPartRepo;
        this.logRepo = logRepo;
    }

    @Override
    public List<BenchPart> findAll() {
        return benchPartRepo.findAll();
    }

    @Override
    public BenchPart findById(Long id) {
        return benchPartRepo.findById(id).orElse(null);
    }

    @Override
    public BenchPart update(Long id, BenchPart benchPart) {
        BenchPart benchPartFromDb = benchPartRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(benchPart, benchPartFromDb, "id");
        loging("Изменение детали", "Изменение в оснастке №" + benchPartFromDb.getBench().getId(), "riggingPart", benchPartFromDb.getId());
        return benchPartRepo.save(benchPartFromDb);
    }

    @Override
    public BenchPart save(BenchPart benchPart) {
         benchPartRepo.save(benchPart);
        loging("Добавление детали", "Добавление в оснастке №" + benchPart.getBench().getId(), "riggingPart", benchPart.getId());
        return benchPart;
    }

    @Override
    public void delete(Long id) {
        benchPartRepo.deleteById(id);
        loging("Удаление детали", "Удаление детали", "riggingPart", id);
    }

    @Override
    public BenchPart changeColor(Long id, String color) {
        BenchPart benchPart = benchPartRepo.findById(id).orElse(null);
        benchPart.setColor(color);
        return benchPartRepo.save(benchPart);
    }

    public void loging(String actionShort, String action, String type, Long id) {
        Log log = new Log();
        log.setAction(actionShort);
        log.setDate(java.util.Calendar.getInstance().getTime());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.setAuthor(authentication.getName());
        log.setDescription( action + " детали №" + id + " в \"Станок\"" );
        log.setType(type);
        log.setElementId(id);
        logRepo.save(log);
    }
}
