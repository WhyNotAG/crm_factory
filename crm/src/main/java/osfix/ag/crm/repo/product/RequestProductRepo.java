package osfix.ag.crm.repo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.domain.product.RequestProduct;

@Repository
public interface RequestProductRepo extends JpaRepository<RequestProduct, Long> {
    void deleteAllByRequest(Request request);
}
