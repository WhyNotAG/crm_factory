package osfix.ag.crm.service.impl.manager;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.manager.PLC;
import osfix.ag.crm.repo.manager.PLCRepo;
import osfix.ag.crm.service.PLCService;

import java.util.List;

@Service
public class PLCServiceImpl implements PLCService {
    private PLCRepo plcRepo;

    public PLCServiceImpl(PLCRepo plcRepo) {
        this.plcRepo = plcRepo;
    }

    @Override
    public List<PLC> findAll() {
        return plcRepo.findAll();
    }

    @Override
    public PLC findById(Long id) {
        return plcRepo.findById(id).orElse(null);
    }

    @Override
    public PLC update(Long id, PLC plc) {
        PLC plcFromDb = plcRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(plc, plcFromDb, "id");
        return plcRepo.save(plcFromDb);
    }

    @Override
    public PLC save(PLC plc) {
        return plcRepo.save(plc);
    }

    @Override
    public void delete(Long id) {
        plcRepo.deleteById(id);
    }
}
