package osfix.ag.crm.service;

import osfix.ag.crm.domain.dispatcher.MainTask;

import java.util.List;

public interface MainTaskService {
    List<MainTask> findAll();
    MainTask findById(Long id);
    MainTask update(Long id, MainTask mainTask);
    MainTask save(MainTask mainTask);
    void delete(Long id);
}