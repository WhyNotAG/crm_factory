package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.repo.product.ProductRepo;
import osfix.ag.crm.service.ProductService;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product update(Long id, Product product) {
        Product productFromDb = productRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(product,productFromDb, "id");
        Product result = productRepo.save(productFromDb);
        return result;
    }
}
