package osfix.ag.crm.service;

import osfix.ag.crm.domain.Detail;
import java.util.List;

public interface DetailService {
    List<Detail> findAll();
    Detail findById(Long id);
    Detail update(Long id, Detail detail);
    Detail save(Detail detail);
    void delete(Long id);
}
