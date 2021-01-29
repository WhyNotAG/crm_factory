package osfix.ag.crm.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Log;
import osfix.ag.crm.repo.LogRepo;
import osfix.ag.crm.service.LogService;


@Service
public class LogServiceImpl implements LogService {
    LogRepo logRepo;

    public LogServiceImpl(LogRepo logRepo) {
        this.logRepo = logRepo;
    }

    @Override
    public Page<Log> findAll(Pageable pageable) {
        return logRepo.findAll(pageable);
    }
}
