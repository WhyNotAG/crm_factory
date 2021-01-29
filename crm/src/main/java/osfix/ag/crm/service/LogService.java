package osfix.ag.crm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import osfix.ag.crm.domain.Log;



public interface LogService {
    Page<Log> findAll(Pageable pageable);
    Page<Log> findAllByType(String type, Pageable pageable);
}
