package osfix.ag.crm.service;

import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.product.Product;

import java.util.Set;

@Service
public interface ProductService {
    Set<Product>findAll();
    Product findById(String id);
}
