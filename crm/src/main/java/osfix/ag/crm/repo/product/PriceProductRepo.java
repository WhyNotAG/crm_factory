package osfix.ag.crm.repo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import osfix.ag.crm.domain.product.PriceProduct;

public interface PriceProductRepo extends JpaRepository<PriceProduct, Long> {
}
