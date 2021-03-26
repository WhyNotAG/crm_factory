package osfix.ag.crm.service.impl.manager;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.manager.Prices;
import osfix.ag.crm.repo.manager.PriceListRepo;
import osfix.ag.crm.service.FileStorageService;
import osfix.ag.crm.service.PriceListService;
import osfix.ag.crm.service.impl.FileStorageServiceImpl;

import java.util.List;

@Service
public class PriceListServiceImpl implements PriceListService {
    PriceListRepo priceListRepo;
    FileStorageService fileStorageService;

    public PriceListServiceImpl(PriceListRepo priceListRepo, FileStorageService fileStorageService) {
        this.priceListRepo = priceListRepo;
        this.fileStorageService = fileStorageService;
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
        Prices priceList = priceListRepo.findById(id).orElse(null);
        if(priceList != null) {
            fileStorageService.deleteFileWithUri(priceList.getUri());
        }
        priceListRepo.deleteById(id);
    }
}
