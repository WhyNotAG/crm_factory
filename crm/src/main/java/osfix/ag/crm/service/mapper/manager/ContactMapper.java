package osfix.ag.crm.service.mapper.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.manager.Contact;
import osfix.ag.crm.repo.manager.ClientRepo;
import osfix.ag.crm.service.dto.manager.ContactDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactMapper implements EntityMapper<Contact, ContactDTO> {
    @Autowired
    private ClientRepo clientRepo;
    @Override
    public Contact toEntity(ContactDTO dto) {
        Contact contact = new Contact();
        contact.setClient(clientRepo.findById(dto.getClientId()).orElse(null));
        contact.setEmail(dto.getEmail());
        contact.setId(dto.getId());
        contact.setLastName(dto.getLastName());
        contact.setName(dto.getName());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setPosition(dto.getPosition());
        contact.setSendingMail(dto.getSendingMail());
        return contact;
    }

    @Override
    public ContactDTO fromEntity(Contact entity) {
        ContactDTO dto = new ContactDTO();
        dto.setClientId(entity.getClient().getId());
        dto.setEmail(entity.getEmail());
        dto.setId(entity.getId());
        dto.setSendingMail(entity.getSendingMail());
        dto.setLastName(entity.getLastName());
        dto.setName(entity.getName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setPosition(entity.getPosition());
        return dto;
    }

    @Override
    public List<Contact> fromDtoList(List<ContactDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContactDTO> toDtoList(List<Contact> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
