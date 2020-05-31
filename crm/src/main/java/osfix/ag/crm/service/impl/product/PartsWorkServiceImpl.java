package osfix.ag.crm.service.impl.product;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.domain.dispatcher.rigging.parts.Part;
import osfix.ag.crm.domain.dispatcher.rigging.parts.PartsWork;
import osfix.ag.crm.repo.rigging.part.*;
import osfix.ag.crm.service.PartsWorkService;
import osfix.ag.crm.service.dto.factory.PartsDTO;
import osfix.ag.crm.service.mapper.factory.PartsMapper;

import java.util.List;

@Service
public class PartsWorkServiceImpl implements PartsWorkService {
    private PartsWorkRepo partsWorkRepo;
    private StampPartRepo stampPartRepo;
    private BenchPartRepo benchPartRepo;
    private DetailPartRepo detailPartRepo;
    private PressPartRepo pressPartRepo;
    private PartsMapper partsMapper;

    public PartsWorkServiceImpl(PartsWorkRepo partsWorkRepo, StampPartRepo stampPartRepo, BenchPartRepo benchPartRepo,
                                DetailPartRepo detailPartRepo, PressPartRepo pressPartRepo,
                                PartsMapper partsMapper) {
        this.partsWorkRepo = partsWorkRepo;
        this.stampPartRepo = stampPartRepo;
        this.benchPartRepo = benchPartRepo;
        this.detailPartRepo = detailPartRepo;
        this.pressPartRepo = pressPartRepo;
        this.partsMapper = partsMapper;
    }

    @Override
    public List<PartsWork> findAll() {
        return partsWorkRepo.findAll();
    }

    @Override
    public PartsWork findById(Long id) {
        return partsWorkRepo.findById(id).orElse(null);
    }

    @Override
    public PartsWork update(Long id, PartsWork partsWork) {
        PartsWork partsWorkFromDb = partsWorkRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(partsWork, partsWorkFromDb, "id");
        return partsWorkRepo.save(partsWorkFromDb);
    }

    @Override
    public PartsWork save(PartsWork partsWork) {
        return partsWorkRepo.save(partsWork);
    }

    @Override
    public PartsDTO getPart(Long id) {
        PartsWork partsWorkFromDb = partsWorkRepo.findById(id).orElse(null);
        System.out.println(partsWorkFromDb.getPartType());
        if (partsWorkFromDb.getPartType().equals("Stamp")) {
            return  partsMapper.fromEntity(stampPartRepo.findById(partsWorkFromDb.getPartId()).orElse(null), "Stamp", partsWorkFromDb.getQuantity());
        }

        if (partsWorkFromDb.getPartType().equals("Bench")) {
            return  partsMapper.fromEntity(benchPartRepo.findById(partsWorkFromDb.getPartId()).orElse(null), "Bench", partsWorkFromDb.getQuantity());
        }

        if (partsWorkFromDb.getPartType().equals("Detail")) {
            return  partsMapper.fromEntity(detailPartRepo.findById(partsWorkFromDb.getPartId()).orElse(null), "Detail", partsWorkFromDb.getQuantity());
        }

        if (partsWorkFromDb.getPartType().equals("Press")) {
            return  partsMapper.fromEntity(pressPartRepo.findById(partsWorkFromDb.getPartId()).orElse(null), "Press", partsWorkFromDb.getQuantity());
        }

        else {return null; }
    }

    @Override
    public void deletePart(Long id, Long part_id, String type) {
        PartsWork partsWork = partsWorkRepo.findByPartIdAndWorkControlIdAndPartType(part_id, id, type);
        System.out.println(partsWork.getPartType());
        partsWorkRepo.delete(partsWork);
    }

    @Override
    public void delete(Long id) {
        partsWorkRepo.deleteById(id);
    }
}
