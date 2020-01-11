package osfix.ag.crm.repo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.product.LepsariProduct;

@Repository
public interface LepsariProductRepo extends JpaRepository<LepsariProduct, Long> {
}
