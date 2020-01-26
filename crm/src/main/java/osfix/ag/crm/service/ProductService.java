package osfix.ag.crm.service;

import java.util.List;
import osfix.ag.crm.domain.Document;
import osfix.ag.crm.domain.product.Product;

public interface ProductService {
    Product findById(Long id);
    Product save(Product product);
    void delete(Long id);
    Product update(Long id, Product product);
    List<Product> findAll();
    List<Product> findByCategory(String category);
}
