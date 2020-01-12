package osfix.ag.crm.repo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.domain.product.ProductCategory;

@Repository
public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {
    ProductCategory findByCategory(String category);
}
