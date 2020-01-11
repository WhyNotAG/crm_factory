package osfix.ag.crm.service;

import osfix.ag.crm.domain.WorkList;

import java.util.List;

public interface WorkListService {
    List<WorkList> findAll();
    WorkList findById(Long id);
    WorkList save(WorkList workList);
    WorkList update(Long id, WorkList workList);
    void delete(Long id);
}
