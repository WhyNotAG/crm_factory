package osfix.ag.crm.service.impl.dispatcher.rigging;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.dispatcher.rigging.Press;
import osfix.ag.crm.domain.dispatcher.rigging.parts.PressPart;
import osfix.ag.crm.repo.rigging.PressRepo;
import osfix.ag.crm.service.PressService;

import java.util.List;
@Service
public class PressServiceImpl implements PressService {
    private PressRepo pressRepo;

    public PressServiceImpl(PressRepo pressRepo) {
        this.pressRepo = pressRepo;
    }

    @Override
    public List<Press> findAll() {
        return pressRepo.findAll();
    }

    @Override
    public Press findById(Long id) {
        return pressRepo.findById(id).orElse(null);
    }

    @Override
    public Press update(Long id, Press press) {
        Press pressFromDb = pressRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(press, pressFromDb, "id");
        return pressRepo.save(pressFromDb);
    }

    @Override
    public Press save(Press press) {
        return pressRepo.save(press);
    }

    @Override
    public void delete(Long id) {
        pressRepo.deleteById(id);
    }

    @Override
    public Press addParts(List<PressPart> pressParts) {
        return null;
    }

    @Override
    public Press changeColor(Long id, String color) {
        Press press = pressRepo.findById(id).orElse(null);
        press.setColor(color);
        return pressRepo.save(press);
    }
}
