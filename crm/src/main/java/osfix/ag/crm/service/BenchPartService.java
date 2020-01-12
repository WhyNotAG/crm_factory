package osfix.ag.crm.service;

import osfix.ag.crm.domain.dispatcher.rigging.parts.BenchPart;

import java.util.List;

public interface BenchPartService {
    List<BenchPart> findAll();
    BenchPart findById(Long id);
    BenchPart update(Long id, BenchPart benchPart);
    BenchPart save(BenchPart benchPart);
    void delete(Long id);
    BenchPart changeColor(Long id, String color);
}
