package osfix.ag.crm.service;

import osfix.ag.crm.domain.product.ProductCategory;
import osfix.ag.crm.service.dto.ProductsCategoryDTO;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> findAll();
    ProductCategory findById(Long id);
    ProductCategory findByCategory(String category);
    ProductCategory save(ProductCategory productCategory);
    ProductCategory update(Long id, ProductCategory productCategory);
    List<ProductCategory> findAllName();
    void delete(Long id);
}
