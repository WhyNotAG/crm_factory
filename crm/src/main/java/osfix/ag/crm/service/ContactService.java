package osfix.ag.crm.service;

import osfix.ag.crm.domain.manager.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    Contact findById(Long id);
    Contact update(Long id, Contact contact);
    Contact save(Contact contact);
    void delete(Long id);
}
