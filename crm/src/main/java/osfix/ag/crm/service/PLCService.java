package osfix.ag.crm.service;

import osfix.ag.crm.domain.manager.PLC;

import java.util.List;

public interface PLCService {
    List<PLC> findAll();
    PLC findById(Long id);
    PLC update(Long id, PLC plc);
    PLC save(PLC plc);
    void delete(Long id);
}
