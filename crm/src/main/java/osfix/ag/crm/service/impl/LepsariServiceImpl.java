package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Lepsari;
import osfix.ag.crm.repo.LepsariRepo;
import osfix.ag.crm.service.LepsariService;

import java.util.List;

@Service
public class LepsariServiceImpl implements LepsariService {
    private LepsariRepo lepsariRepo;

    public LepsariServiceImpl(LepsariRepo lepsariRepo) { this.lepsariRepo = lepsariRepo; }

    @Override
    public Lepsari findById(Long id) { return lepsariRepo.findById(id).orElse(null); }

    @Override
    public Lepsari save(Lepsari lepsari) { return lepsariRepo.save(lepsari); }

    @Override
    public void delete(Long id) { lepsariRepo.deleteById(id); }

    @Override
    public Lepsari update(Long id, Lepsari lepsari) {
        Lepsari lepsariFromDb = lepsariRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(lepsari,lepsariFromDb,"id");
        return lepsariRepo.save(lepsariFromDb);
    }

    @Override
    public List<Lepsari> findAll() { return lepsariRepo.findAll(); }

    @Override
    public void changeStatus(Long id, String status) {
        Lepsari lepsari = lepsariRepo.findById(id).orElse(null);
        lepsari.setStatus(status);
        lepsariRepo.save(lepsari);
    }
}
