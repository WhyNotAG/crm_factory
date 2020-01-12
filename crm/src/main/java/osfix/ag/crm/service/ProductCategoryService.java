package osfix.ag.crm.service;

import osfix.ag.crm.domain.product.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> findAll();
    ProductCategory findById(Long id);
    ProductCategory findByCategory(String category);
    ProductCategory save(ProductCategory productCategory);
    ProductCategory update(Long id, ProductCategory productCategory);
    void delete(Long id);
}
