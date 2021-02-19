package osfix.ag.crm.service.impl.admin;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.admin.Journal;
import osfix.ag.crm.repo.admin.JournalRepo;
import osfix.ag.crm.service.JournalService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {
    private JournalRepo journalRepo;


    public JournalServiceImpl(JournalRepo journalRepo) {
        this.journalRepo = journalRepo;
    }

    @Override
    public List<Journal> findAll() {
        return journalRepo.findAll();
    }

    @Override
    public Journal save(Journal journal) {
        return journalRepo.save(journal);
    }

    @Override
    public Journal update(Long id, Journal journal) {
        Journal journalFromDb = journalRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(journal, journalFromDb, "id");
        return journalRepo.save(journalFromDb);
    }

    @Override
    public void delete(Long id) {
        journalRepo.deleteById(id);
    }

    @Override
    public Journal findById(Long id) {
        return journalRepo.findById(id).orElse(null);
    }

    @Override
    public List<Journal> findAllByDate(Date date) {
        Timestamp ts=new Timestamp(date.getTime() * 1000);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(ts.getTime()));
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        List<Journal> journals = journalRepo.findAll();
        List<Journal> journalsRes = new ArrayList<>();
        for(Journal journal : journals) {
            c.setTime(journal.getDate());
            if( c.get(Calendar.DAY_OF_MONTH) == day && c.get(Calendar.MONTH) == month && c.get(Calendar.YEAR) == year) {
                journalsRes.add(journal);
            }
        }

        return journalsRes;
    }
}
