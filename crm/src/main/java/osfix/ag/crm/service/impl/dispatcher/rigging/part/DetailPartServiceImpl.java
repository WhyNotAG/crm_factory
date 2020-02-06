package osfix.ag.crm.service.impl.dispatcher.rigging.part;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.dispatcher.rigging.parts.DetailPart;
import osfix.ag.crm.repo.rigging.part.DetailPartRepo;
import osfix.ag.crm.service.DetailPartService;

import java.util.List;

@Service
public class DetailPartServiceImpl implements DetailPartService {
    private DetailPartRepo detailPartRepo;

    public DetailPartServiceImpl(DetailPartRepo detailPartRepo) {
        this.detailPartRepo = detailPartRepo;
    }

    @Override
    public List<DetailPart> findAll() { return detailPartRepo.findAll(); }

    @Override
    public DetailPart findById(Long id) { return detailPartRepo.findById(id).orElse(null); }

    @Override
    public DetailPart update(Long id, DetailPart detailPart) {
        DetailPart detailPartFromDb = detailPartRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(detailPart, detailPartFromDb, "id");
        return detailPartRepo.save(detailPartFromDb);
    }

    @Override
    public DetailPart save(DetailPart detailPart) { return detailPartRepo.save(detailPart); }

    @Override
    public void delete(Long id) { detailPartRepo.deleteById(id);}

    @Override
    public DetailPart changeColor(Long id, String color) {
        DetailPart detailPart = detailPartRepo.findById(id).orElse(null);
        detailPart.setColor(color);
        return detailPartRepo.save(detailPart);
    }
}
