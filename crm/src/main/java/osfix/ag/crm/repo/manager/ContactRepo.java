package osfix.ag.crm.repo.manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osfix.ag.crm.domain.manager.Contact;

import java.util.List;
import java.util.Set;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {
    Set<Contact> findAllByPhoneNumberContainsOrEmailContainsOrLastNameContains(String phone, String email, String lastName);
}
