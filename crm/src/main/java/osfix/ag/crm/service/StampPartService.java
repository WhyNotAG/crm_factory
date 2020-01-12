package osfix.ag.crm.service;

import osfix.ag.crm.domain.dispatcher.rigging.parts.StampPart;

import java.util.List;

public interface StampPartService {
    List<StampPart> findAll();
    StampPart findById(Long id);
    StampPart update(Long id, StampPart stampPart);
    StampPart save(StampPart stampPart);
    void delete(Long id);
    StampPart changeColor(Long id, String color);
}
