package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.InvoicingRequest;

@Repository
public interface InvoicingRequestRepo  extends JpaRepository<InvoicingRequest, Long> {
}
