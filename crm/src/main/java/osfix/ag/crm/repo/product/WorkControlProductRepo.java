package osfix.ag.crm.repo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.domain.product.WorkControlProduct;

import java.util.List;

public interface WorkControlProductRepo extends JpaRepository<WorkControlProduct, Long> {
    WorkControlProduct findByWorkControlAndProduct(WorkControl workControl, Product product);
}
