package osfix.ag.crm.service;

import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.domain.admin.Journal;

import java.util.Date;
import java.util.List;

public interface JournalService {
    List<Journal> findAll();
    Journal save(Journal journal);
    Journal update(Long id, Journal journal);
    void delete(Long id);
    Journal findById(Long id);
    List<Journal> findAllByDate(Date date);
}
