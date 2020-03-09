package osfix.ag.crm.service.impl.manager;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.manager.Contact;
import osfix.ag.crm.repo.manager.ContactRepo;
import osfix.ag.crm.service.ContactService;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    private ContactRepo contactRepo;

    public ContactServiceImpl(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    @Override
    public List<Contact> findAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contact findById(Long id) {
        return contactRepo.findById(id).orElse(null);
    }

    @Override
    public Contact update(Long id, Contact contact) {
        Contact contactFromDb = contactRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(contact, contactFromDb, "id");
        return contactRepo.save(contactFromDb);
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepo.save(contact);
    }

    @Override
    public void delete(Long id) {
        contactRepo.deleteById(id);
    }
}
