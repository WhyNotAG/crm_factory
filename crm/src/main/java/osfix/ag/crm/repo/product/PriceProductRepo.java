package osfix.ag.crm.repo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.product.PriceProduct;
@Repository
public interface PriceProductRepo extends JpaRepository<PriceProduct, Long> {
}
