package osfix.ag.crm.repo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import osfix.ag.crm.domain.product.PriceGroupProduct;

public interface PriceGroupProductRepo extends JpaRepository<PriceGroupProduct, Long> {
}
