package osfix.ag.crm.service.impl;

import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.LEMZ;
import osfix.ag.crm.repo.LEMZRepo;
import osfix.ag.crm.service.LEMZService;

import java.util.List;

@Service
public class LEMZServiceImpl implements LEMZService {
    private LEMZRepo lemzRepo;

    public LEMZServiceImpl(LEMZRepo lemzRepo) {
        this.lemzRepo = lemzRepo;
    }

    @Override
    public LEMZ findById(Long id) {
        return lemzRepo.findById(id).orElse(null);
    }

    @Override
    public LEMZ save(LEMZ lemz) {
        return lemzRepo.save(lemz);
    }

    @Override
    public void delete(Long id) {
        lemzRepo.deleteById(id);
    }

    @Override
    public LEMZ update(Long id, LEMZ lemz) {
        return null;
    }

    @Override
    public void changeStatus(Long id, String status) {
        LEMZ lemz = lemzRepo.findById(id).orElse(null);
        lemz.setStatus(status);
        lemzRepo.save(lemz);
    }
    @Override
    public List<LEMZ> findAll() {
        return lemzRepo.findAll();
    }
}
