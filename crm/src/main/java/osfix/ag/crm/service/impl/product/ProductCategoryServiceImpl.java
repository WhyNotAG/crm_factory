package osfix.ag.crm.service.impl.product;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.product.ProductCategory;
import osfix.ag.crm.repo.product.ProductCategoryRepo;
import osfix.ag.crm.service.ProductCategoryService;
import osfix.ag.crm.service.dto.ProductsCategoryDTO;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private ProductCategoryRepo productCategoryRepo;

    public ProductCategoryServiceImpl(ProductCategoryRepo productCategoryRepo) {
        this.productCategoryRepo = productCategoryRepo;
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepo.findAll();
    }

    @Override
    public ProductCategory findById(Long id) {
        return productCategoryRepo.findById(id).orElse(null);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepo.save(productCategory);
    }

    @Override
    public ProductCategory update(Long id, ProductCategory productCategory) {
        ProductCategory productCategoryFromDb = productCategoryRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(productCategory, productCategoryFromDb, "id");
        return productCategoryRepo.save(productCategoryFromDb);
    }

    @Override
    public void delete(Long id) {
         productCategoryRepo.deleteById(id);
    }

    @Override
    public List<ProductCategory> findAllName() {
        return productCategoryRepo.findAll();
    }

    @Override
    public ProductCategory findByCategory(String category) {
        return productCategoryRepo.findByCategory(category);
    }
}
