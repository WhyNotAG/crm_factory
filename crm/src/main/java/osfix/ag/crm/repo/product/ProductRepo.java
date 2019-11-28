package osfix.ag.crm.repo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import osfix.ag.crm.domain.product.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
