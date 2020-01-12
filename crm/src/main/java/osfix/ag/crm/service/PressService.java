package osfix.ag.crm.service;

import osfix.ag.crm.domain.dispatcher.rigging.Press;
import osfix.ag.crm.domain.dispatcher.rigging.parts.PressPart;

import java.util.List;

public interface PressService {
    List<Press> findAll();
    Press findById(Long id);
    Press update(Long id, Press press);
    Press save(Press press);
    void delete(Long id);
    Press addParts(List<PressPart> pressParts);
    Press changeColor(Long id, String color);
}
