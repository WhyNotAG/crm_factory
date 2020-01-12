package osfix.ag.crm.service.impl.dispatcher.rigging.part;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.dispatcher.rigging.parts.BenchPart;
import osfix.ag.crm.repo.rigging.part.BenchPartRepo;
import osfix.ag.crm.service.BenchPartService;

import java.util.List;

@Service
public class BenchPartServiceImpl implements BenchPartService {
    private BenchPartRepo benchPartRepo;

    public BenchPartServiceImpl(BenchPartRepo benchPartRepo) {
        this.benchPartRepo = benchPartRepo;
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
        return benchPartRepo.save(benchPartFromDb);
    }

    @Override
    public BenchPart save(BenchPart benchPart) {
        return benchPartRepo.save(benchPart);
    }

    @Override
    public void delete(Long id) {
        benchPartRepo.deleteById(id);
    }

    @Override
    public BenchPart changeColor(Long id, String color) {
        BenchPart benchPart = benchPartRepo.findById(id).orElse(null);
        benchPart.setColor(color);
        return benchPartRepo.save(benchPart);
    }
}
