package osfix.ag.crm.service;

import osfix.ag.crm.domain.dispatcher.rigging.parts.DetailPart;

import java.util.List;

public interface DetailPartService {
    List<DetailPart> findAll();
    DetailPart findById(Long id);
    DetailPart update(Long id, DetailPart detailPart);
    DetailPart save(DetailPart detailPart);
    void delete(Long id);
    DetailPart changeColor(Long id, String color);
}
