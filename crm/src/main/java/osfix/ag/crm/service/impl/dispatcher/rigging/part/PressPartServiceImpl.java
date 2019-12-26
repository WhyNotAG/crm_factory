package osfix.ag.crm.service.impl.dispatcher.rigging.part;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.dispatcher.rigging.parts.PressPart;
import osfix.ag.crm.repo.rigging.part.PressPartRepo;
import osfix.ag.crm.service.PressPartService;

import java.util.List;

@Service
public class PressPartServiceImpl implements PressPartService {
    private PressPartRepo pressPartRepo;

    public PressPartServiceImpl(PressPartRepo pressPartRepo) {
        this.pressPartRepo = pressPartRepo;
    }

    @Override
    public List<PressPart> findAll() {
        return pressPartRepo.findAll();
    }

    @Override
    public PressPart findById(Long id) {
        return pressPartRepo.findById(id).orElse(null);
    }

    @Override
    public PressPart update(Long id, PressPart pressPart) {
        PressPart pressPartFromDb = pressPartRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(pressPart, pressPartFromDb, "id");
        return pressPartRepo.save(pressPartFromDb);
    }

    @Override
    public PressPart save(PressPart pressPart) {
        return pressPartRepo.save(pressPart);
    }

    @Override
    public void delete(Long id) {
        pressPartRepo.deleteById(id);
    }
}
