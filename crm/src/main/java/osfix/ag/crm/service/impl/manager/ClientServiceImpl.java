package osfix.ag.crm.service.impl.manager;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.manager.Client;
import osfix.ag.crm.repo.manager.ClientRepo;
import org.springframework.data.domain.Page;
import osfix.ag.crm.service.ClientService;


import java.sql.Date;
import java.util.List;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepo clientRepo;

    public ClientServiceImpl(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public Page<Client> findAll(Pageable pageable) {
        return clientRepo.findAll(pageable);
    }

    @Override
    public Set<Client> search(String substring, String type) {
        return clientRepo.findAllByNameIgnoreCaseContainsAndTypeOrCommentIgnoreCaseContainsAndTypeOrSiteIgnoreCaseContainsAndType(substring, type, substring, type, substring, type);
    }

    @Override
    public Client findById(Long id) {
        return clientRepo.findById(id).orElse(null);
    }

    @Override
    public Client update(Long id, Client client) {
        Client clientFromDb = clientRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(client, clientFromDb, "id");
        return clientRepo.save(clientFromDb);
    }

    @Override
    public Client save(Client client) {
        return clientRepo.save(client);
    }

    @Override
    public void delete(Long id) {
        clientRepo.deleteById(id);
    }

    @Override
    public Page<Client> findAllByCategory_Name(String name, Pageable pageable) {
        return clientRepo.findAllByCategory_Name(name, pageable);
    }

    @Override
    public Page<Client> findAllByCategory_NameAndClientType(String name, String clientType, Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String hasUserRole = authentication.getAuthorities().toString();
        if(hasUserRole.equals("ROLE_ADMIN"))
            return clientRepo.findAllByCategory_NameAndClientType(name, clientType, pageable);
        else return null;
    }

    @Override
    public Page<Client> findAllByCategory_NameAndClientTypeAndTypeOrType(String name, String clientType, String type, String type2, Pageable pageable) {
        return clientRepo.findAllByCategory_NameAndClientTypeAndType(name, clientType, type, pageable);
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
