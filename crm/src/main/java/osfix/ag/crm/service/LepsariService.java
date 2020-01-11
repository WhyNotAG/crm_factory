package osfix.ag.crm.service;

import osfix.ag.crm.domain.Lepsari;

import java.util.List;

public interface LepsariService {
    Lepsari findById(Long id);
    Lepsari save(Lepsari lepsari);
    void delete(Long id);
    Lepsari update(Long id, Lepsari lepsari);
    List<Lepsari> findAll();
    void changeStatus(Long id, String status);
}
