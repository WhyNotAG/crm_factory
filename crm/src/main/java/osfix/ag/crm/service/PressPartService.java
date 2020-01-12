package osfix.ag.crm.service;

import osfix.ag.crm.domain.dispatcher.rigging.parts.PressPart;

import java.util.List;

public interface PressPartService {
    List<PressPart> findAll();
    PressPart findById(Long id);
    PressPart update(Long id, PressPart pressPart);
    PressPart save(PressPart pressPart);
    void delete(Long id);
    PressPart changeColor(Long id, String color);
}
