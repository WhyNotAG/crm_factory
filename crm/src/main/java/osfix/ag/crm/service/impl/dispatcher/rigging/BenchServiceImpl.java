package osfix.ag.crm.service.impl.dispatcher.rigging;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Log;
import osfix.ag.crm.domain.dispatcher.rigging.Bench;
import osfix.ag.crm.domain.dispatcher.rigging.parts.BenchPart;
import osfix.ag.crm.repo.LogRepo;
import osfix.ag.crm.repo.rigging.BenchRepo;
import osfix.ag.crm.service.BenchService;

import java.util.List;
@Service
public class BenchServiceImpl implements BenchService {
    private BenchRepo benchRepo;
    private LogRepo logRepo;

    public BenchServiceImpl(BenchRepo benchRepo, LogRepo logRepo) {
        this.benchRepo = benchRepo;
        this.logRepo = logRepo;
    }

    @Override
    public List<Bench> findAll() {
        return benchRepo.findAll();
    }

    @Override
    public Bench findById(Long id) {
        return benchRepo.findById(id).orElse(null);
    }

    @Override
    public Bench update(Long id, Bench bench) {
        Bench benchFromDb = benchRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(bench, benchFromDb, "id");
        loging("Изменение", "Изменение", "rigging", benchFromDb.getId());
        return benchRepo.save(benchFromDb);
    }

    @Override
    public Bench save(Bench bench) {
        benchRepo.save(bench);
        loging("Создание", "Создание", "rigging", bench.getId());
        return bench;
    }

    @Override
    public void delete(Long id) {
        benchRepo.deleteById(id);
        loging("Удаление", "Удаление", "rigging", id);
    }

    @Override
    public Bench addParts(List<BenchPart> benchParts) {
        return null;
    }

    @Override
    public Bench changeColor(Long id, String color) {
        Bench bench = benchRepo.findById(id).orElse(null);
        bench.setColor(color);
        return benchRepo.save(bench);
    }

    public void loging(String actionShort, String action, String type, Long id) {
        Log log = new Log();
        log.setAction(actionShort);
        log.setDate(java.util.Calendar.getInstance().getTime());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.setAuthor(authentication.getName());
        log.setDescription( action + " оснастки №" + id + " в \"Станок\"" );
        log.setType(type);
        log.setElementId(id);
        logRepo.save(log);
    }
}
