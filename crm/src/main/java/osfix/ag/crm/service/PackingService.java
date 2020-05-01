package osfix.ag.crm.service;

import osfix.ag.crm.domain.dispatcher.rigging.Press;
import osfix.ag.crm.domain.factory.Packing;
import osfix.ag.crm.service.dto.factory.PackingDTO;

import java.util.List;

public interface PackingService {
    List<Packing> findAll();
    Packing findById(Long id);
    Packing update(Long id, Packing packing);
    Packing save(Packing packing);
    void delete(Long id);
}
