package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.product.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
