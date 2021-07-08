package osfix.ag.crm.repo.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import osfix.ag.crm.domain.admin.MessageTemplate;

public interface MessageTemplateRepo extends JpaRepository<MessageTemplate, Long> {
}
