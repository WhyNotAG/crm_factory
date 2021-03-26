package osfix.ag.crm.service;

import osfix.ag.crm.domain.manager.Prices;

import java.util.List;

public interface PriceListService {
    List<Prices> findAll();
    Prices findById(Long id);
    Prices save(Prices prices);
    Prices update (Long id, Prices prices);
    void delete(Long id);
}
