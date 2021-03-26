package osfix.ag.crm.service.impl.manager;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.manager.Prices;
import osfix.ag.crm.repo.manager.PriceListRepo;
import osfix.ag.crm.service.PriceListService;

import java.util.List;

@Service
public class PriceListServiceImpl implements PriceListService {
    PriceListRepo priceListRepo;

    public PriceListServiceImpl(PriceListRepo priceListRepo) {
        this.priceListRepo = priceListRepo;
    }

    @Override
    public List<Prices> findAll() {
        return priceListRepo.findAll();
    }

    @Override
    public Prices findById(Long id) {
        return priceListRepo.findById(id).orElse(null);
    }

    @Override
    public Prices save(Prices prices) {
        return priceListRepo.save(prices);
    }

    @Override
    public Prices update(Long id, Prices prices) {
        Prices pricesFromDb = priceListRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(prices, pricesFromDb, "id");
        return priceListRepo.save(pricesFromDb);
    }

    @Override
    public void delete(Long id) {
        priceListRepo.deleteById(id);
    }
}
