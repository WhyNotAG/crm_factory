package osfix.ag.crm.service;

import osfix.ag.crm.domain.dispatcher.Detail;
import osfix.ag.crm.domain.dispatcher.ShippingRegistry;

import java.util.List;

public interface ShippingRegistryService {
    List<ShippingRegistry> findAll();
    ShippingRegistry findById(Long id);
    ShippingRegistry update(Long id, ShippingRegistry shippingRegistry);
    ShippingRegistry save(ShippingRegistry shippingRegistry);
    void delete(Long id);
}
