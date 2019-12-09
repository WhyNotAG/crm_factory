package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Client;
import osfix.ag.crm.domain.Detail;
import osfix.ag.crm.repo.DetailRepo;
import osfix.ag.crm.service.DetailService;

import java.util.List;

@Service
public class DetailServiceImpl implements DetailService {
    private DetailRepo detailRepo;

    public DetailServiceImpl(DetailRepo detailRepo) { this.detailRepo = detailRepo; }

    @Override
    public List<Detail> findAll() { return detailRepo.findAll(); }

    @Override
    public Detail findById(Long id) { return detailRepo.findById(id).orElse(null); }

    @Override
    public Detail update(Long id, Detail detail) {
        Detail detailFromDb = detailRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(detail,detailFromDb, "id");
        return detailRepo.save(detailFromDb);
    }

    @Override
    public Detail save(Detail detail) { return detailRepo.save(detail); }

    @Override
    public void delete(Long id) { detailRepo.deleteById(id); }
}
