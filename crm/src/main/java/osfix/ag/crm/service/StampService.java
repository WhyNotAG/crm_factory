package osfix.ag.crm.service;

import osfix.ag.crm.domain.dispatcher.rigging.Stamp;
import osfix.ag.crm.domain.dispatcher.rigging.parts.StampPart;

import java.util.List;

public interface StampService {
    List<Stamp> findAll();
    Stamp findById(Long id);
    Stamp update(Long id, Stamp stamp);
    Stamp save(Stamp stamp);
    void delete(Long id);
    Stamp addParts(List<StampPart> stampParts);
}
