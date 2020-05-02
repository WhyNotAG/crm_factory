package osfix.ag.crm.service.impl.product;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.factory.Packing;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.repo.factory.PackingRepo;
import osfix.ag.crm.repo.product.ProductCategoryRepo;
import osfix.ag.crm.repo.product.ProductRepo;
import osfix.ag.crm.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;
    private PackingRepo packingRepo;
    //private ProductCategoryRepo productCategoryRepo;

    public ProductServiceImpl(ProductRepo productRepo, ProductCategoryRepo productCategoryRepo, PackingRepo packingRepo) {
        this.productRepo = productRepo;
        //this.productCategoryRepo = productCategoryRepo;
        this.packingRepo = packingRepo;
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

    @Override
    public List<Product> findByCategory(String category) {
        List<Product> products = new ArrayList<>();
        for(Product product : productRepo.findAll()) {
            if(product.getProductCategory() != null) {
                if (product.getProductCategory().getCategory().equals(category)) {
                    products.add(product);
                }
            }
        }
        return products;
    }

    @Override
    public List<Product> findByProductLocation(String location) {
        return productRepo.findAllByProductionLocation(location);
    }

    @Override
    public Product addPacking(Long productId, List<Long> packingId) {
        Product product = productRepo.findById(productId).orElse(null);
        if(packingId != null) {
            List<Packing> packings = new ArrayList<>();
            for (Long packing : packingId) {
                packings.add(packingRepo.findById(packing).orElse(null));
            }
            product.setPackings(packings);
        } else product.setPackings(null);
        return productRepo.save(product);
    }
}
