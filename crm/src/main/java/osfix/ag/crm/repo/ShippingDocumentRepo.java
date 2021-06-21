package osfix.ag.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.ShippingDocument;

@Repository
public interface ShippingDocumentRepo extends JpaRepository<ShippingDocument, Long> {
}
