package osfix.ag.crm.service;

import osfix.ag.crm.domain.dispatcher.rigging.Bench;
import osfix.ag.crm.domain.dispatcher.rigging.parts.BenchPart;

import java.util.List;

public interface BenchService {
    List<Bench> findAll();
    Bench findById(Long id);
    Bench update(Long id, Bench bench);
    Bench save(Bench bench);
    void delete(Long id);
    Bench addParts(List<BenchPart> benchParts);
    Bench changeColor(Long id, String color);
}
