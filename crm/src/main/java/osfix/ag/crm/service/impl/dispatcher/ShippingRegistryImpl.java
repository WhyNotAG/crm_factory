package osfix.ag.crm.service.impl.dispatcher;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.dispatcher.ShippingRegistry;
import osfix.ag.crm.repo.ShippingRegistryRepo;
import osfix.ag.crm.service.ShippingRegistryService;
import java.util.List;

@Service
public class ShippingRegistryImpl implements ShippingRegistryService {
    private ShippingRegistryRepo shippingRegistryRepo;

    public ShippingRegistryImpl(ShippingRegistryRepo shippingRegistryRepo) { this.shippingRegistryRepo = shippingRegistryRepo; }

    @Override
    public List<ShippingRegistry> findAll() { return shippingRegistryRepo.findAll(); }

    @Override
    public ShippingRegistry findById(Long id) { return shippingRegistryRepo.findById(id).orElse(null); }

    @Override
    public ShippingRegistry update(Long id, ShippingRegistry shippingRegistry) {
        ShippingRegistry shippingRegistryFromDb = shippingRegistryRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(shippingRegistry, shippingRegistryFromDb, "id");
        return shippingRegistryRepo.save(shippingRegistryFromDb);
    }

    @Override
    public ShippingRegistry save(ShippingRegistry shippingRegistry) { return shippingRegistryRepo.save(shippingRegistry); }

    @Override
    public void delete(Long id) { shippingRegistryRepo.deleteById(id);}
}
