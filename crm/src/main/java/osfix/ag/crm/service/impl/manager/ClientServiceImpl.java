package osfix.ag.crm.service.impl.manager;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.manager.Client;
import osfix.ag.crm.domain.manager.Contact;
import osfix.ag.crm.domain.manager.LegalEntity;
import osfix.ag.crm.domain.user.User;
import osfix.ag.crm.repo.manager.ClientRepo;
import org.springframework.data.domain.Page;
import osfix.ag.crm.repo.manager.ContactRepo;
import osfix.ag.crm.repo.manager.LegalEntityRepo;
import osfix.ag.crm.repo.user.UserRepo;
import osfix.ag.crm.service.ClientService;
import osfix.ag.crm.service.dto.manager.ClientDTO;
import osfix.ag.crm.service.mapper.manager.ClientMapper;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepo clientRepo;
    private ClientMapper clientMapper;
    private LegalEntityRepo legalEntityRepo;
    private ContactRepo contactRepo;
    private UserRepo userRepo;

    public ClientServiceImpl(ClientRepo clientRepo, ClientMapper clientMapper, UserRepo userRepo,
                             LegalEntityRepo legalEntityRepo, ContactRepo contactRepo) {
        this.clientRepo = clientRepo;
        this.clientMapper = clientMapper;
        this.userRepo = userRepo;
        this.contactRepo = contactRepo;
        this.legalEntityRepo = legalEntityRepo;
    }

    @Override
    public Page<Client> findAll(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String hasUserRole = authentication.getAuthorities().toString();
        System.out.println(hasUserRole);
        if(hasUserRole.equals("ROLE_ADMIN"))
            return clientRepo.findAll(pageable);
        return clientRepo.findAllByUserIsNull(pageable);
    }

    @Override
    public Set<Client> search(String substring, String type) {
        Set<Contact> contacts = contactRepo.findAllByPhoneNumberContainsOrEmailIgnoreCaseContainsOrLastNameIgnoreCaseContains(substring, substring, substring);
        Set<LegalEntity> legalEntities = legalEntityRepo.findAllByInnIgnoreCaseContains(substring);
        Set<Client> searchingClient = new HashSet<>();

        for(Contact contact : contacts) { searchingClient.addAll(clientRepo.findAllByContacts(contact)); }
        for(LegalEntity legalEntity : legalEntities) { searchingClient.addAll(clientRepo.findAllByLegalEntities(legalEntity)); }
        searchingClient.addAll(clientRepo.findAllByNameIgnoreCaseContainsAndTypeOrCommentIgnoreCaseContainsAndTypeOrSiteIgnoreCaseContainsAndType(substring, type, substring, type, substring, type));
        return searchingClient;
    }

    @Override
    public Client findById(Long id) {
        return clientRepo.findById(id).orElse(null);
    }

    @Override
    public Client update(Long id, ClientDTO client) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String hasUserRole = authentication.getAuthorities().toString();
        Client entity = clientMapper.toEntity(client);
        Client clientFromDb = clientRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(entity, clientFromDb, "id");
        if (client.getIsClosed() == null) client.setIsClosed(false);
        if(client.getIsClosed()) {
            entity.setUser(userRepo.findByUsername(authentication.getName()));
            return clientRepo.save(clientFromDb);
        }
        return clientRepo.save(clientFromDb);
    }

    @Override
    public Client save(ClientDTO client) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String hasUserRole = authentication.getAuthorities().toString();
        Client entity = clientMapper.toEntity(client);
        if(client.getIsClosed()) {
            entity.setUser(userRepo.findByUsername(authentication.getName()));
            return clientRepo.save(entity);
        }
        return clientRepo.save(entity);
    }

    @Override
    public void delete(Long id) {
        clientRepo.deleteById(id);
    }

    @Override
    public Page<Client> findAllByCategory_Name(String name, Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        if(hasUserRole)
            return clientRepo.findAllByCategory_Name(name, pageable);
        return clientRepo.findAllByCategory_NameAndUserIsNull(name, pageable);
    }

    @Override
    public Page<Client> findAllByCategory_NameAndClientType(String name, String clientType, Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        if(hasUserRole)
            return clientRepo.findAllByCategory_NameAndClientType(name, clientType, pageable);
        return clientRepo.findAllByCategory_NameAndClientTypeAndUserIsNull(name, clientType, pageable);
    }

    @Override
    public Page<Client> findAllByCategory_NameAndClientTypeAndTypeOrType(String name, String clientType, String type, String type2, Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        if(hasUserRole)
            return clientRepo.findAllByCategory_NameAndClientTypeAndType(name, clientType, type, pageable); ;
        return clientRepo.findAllByCategory_NameAndClientTypeAndTypeAndUserIsNull(name, clientType, type, pageable);
    }

    @Override
    public Client updateDate(Long id, Long date) {
        Client client = clientRepo.findById(id).orElse(null);
        client.setNextDateContact(new Date(date*1000));
        return clientRepo.save(client);
    }

    @Override
    public Page<Client> findAllByCategory_NameAndClientTypeAndTypeIn(List<String> name, String clientType, String type, Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        if(hasUserRole)
            return clientRepo.findAllByCategory_NameInAndClientTypeAndType(name, clientType, type, pageable);
        return null;
    }
}
