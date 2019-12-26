package osfix.ag.crm.service.impl.dispatcher.rigging;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.dispatcher.rigging.Bench;
import osfix.ag.crm.domain.dispatcher.rigging.parts.BenchPart;
import osfix.ag.crm.repo.rigging.BenchRepo;
import osfix.ag.crm.service.BenchService;

import java.util.List;
@Service
public class BenchServiceImpl implements BenchService {
    private BenchRepo benchRepo;

    public BenchServiceImpl(BenchRepo benchRepo) {
        this.benchRepo = benchRepo;
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
        return benchRepo.save(benchFromDb);
    }

    @Override
    public Bench save(Bench bench) {
        return benchRepo.save(bench);
    }

    @Override
    public void delete(Long id) { benchRepo.deleteById(id);}

    @Override
    public Bench addParts(List<BenchPart> benchParts) {
        return null;
    }
}
