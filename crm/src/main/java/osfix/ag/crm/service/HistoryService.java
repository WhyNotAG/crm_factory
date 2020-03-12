package osfix.ag.crm.service;

import osfix.ag.crm.domain.manager.History;

import java.util.List;

public interface HistoryService {
    List<History> findAll();
    History findById(Long id);
    History update(Long id, History history);
    History save(History history);
    void delete(Long id);
}
