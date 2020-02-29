package osfix.ag.crm.repo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.product.PriceGroupProduct;

@Repository
public interface PriceGroupProductRepo extends JpaRepository<PriceGroupProduct, Long> {
    PriceGroupProduct findByName(String name);
}
