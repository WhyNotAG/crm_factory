package osfix.ag.crm.service;


import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.domain.dispatcher.rigging.parts.Part;
import osfix.ag.crm.domain.dispatcher.rigging.parts.PartsWork;
import osfix.ag.crm.service.dto.factory.PartsDTO;

import java.util.List;

public interface PartsWorkService {
    List<PartsWork> findAll();
    PartsWork findById(Long id);
    PartsWork update(Long id,  PartsWork partsWork);
    PartsWork save( PartsWork partsWork);
    PartsDTO getPart(Long id);
    void deletePart(Long id, Long part_id, String type);
    void delete(Long id);
}
