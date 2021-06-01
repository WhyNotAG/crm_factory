package osfix.ag.crm.repo.factory;

import org.springframework.data.jpa.repository.JpaRepository;
import osfix.ag.crm.domain.factory.Goods;
import osfix.ag.crm.domain.product.Product;

import java.util.List;

public interface GoodsRepo  extends JpaRepository<Goods, Long> {
    List<Goods> findAllByProduct(Product product);
}
