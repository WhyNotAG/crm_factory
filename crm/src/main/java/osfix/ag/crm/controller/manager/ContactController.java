package osfix.ag.crm.controller.manager;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.manager.Contact;
import osfix.ag.crm.service.ContactService;
import osfix.ag.crm.service.dto.manager.ContactDTO;
import osfix.ag.crm.service.mapper.manager.ContactMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {
    public ContactService contactService;
    private ContactMapper contactMapper;

    public ContactController(ContactService contactService, ContactMapper contactMapper) {
        this.contactMapper = contactMapper;
        this.contactService = contactService;
    }

    @GetMapping("/")
    public List<Contact> getAll() { return contactService.findAll();}

    @GetMapping("/{id}")
    public Contact getById(@PathVariable(name="id") long id) {return contactService.findById(id);}

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping()
    public Contact create(@RequestBody ContactDTO contact) {
        return contactService.save(contactMapper.toEntity(contact));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name="id") long id) { contactService.delete(id);}

    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @PutMapping("/{id}")
    public Contact update(@PathVariable(name="id") Long id, @RequestBody ContactDTO contact) {
        return contactService.update(id, contactMapper.toEntity(contact));
    }
}
