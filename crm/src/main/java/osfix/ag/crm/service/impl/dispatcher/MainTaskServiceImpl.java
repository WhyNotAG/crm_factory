package osfix.ag.crm.service.impl.dispatcher;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.dispatcher.MainTask;
import osfix.ag.crm.repo.MainTaskRepo;
import osfix.ag.crm.service.MainTaskService;

import java.util.List;

@Service
public class MainTaskServiceImpl implements MainTaskService {
    private MainTaskRepo mainTaskRepo;

    public MainTaskServiceImpl(MainTaskRepo mainTaskRepo) { this.mainTaskRepo = mainTaskRepo; }

    @Override
    public List<MainTask> findAll() {
        return mainTaskRepo.findAll();
    }

    @Override
    public MainTask findById(Long id) {
        return mainTaskRepo.findById(id).orElse(null);
    }

    @Override
    public MainTask update(Long id, MainTask mainTask) {
        MainTask mainTaskFromDb = mainTaskRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(mainTask, mainTaskFromDb, "id");
        return mainTaskRepo.save(mainTaskFromDb);
    }

    @Override
    public MainTask save(MainTask mainTask) {
        return mainTaskRepo.save(mainTask);
    }

    @Override
    public void delete(Long id) {
        mainTaskRepo.deleteById(id);
    }
}
