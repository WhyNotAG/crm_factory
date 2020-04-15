package osfix.ag.crm.service.impl.manager;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.manager.Client;
import osfix.ag.crm.repo.manager.ClientRepo;
import org.springframework.data.domain.Page;
import osfix.ag.crm.service.ClientService;


import java.sql.Date;
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
    public Set<Client> search(String substring) {
        return clientRepo.findAllByNameContainsOrCommentContainsOrSiteContains(substring, substring, substring);
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
        return clientRepo.findAllByCategory_NameAndClientType(name, clientType, pageable);
    }

    @Override
    public Client updateDate(Long id, Long date) {
        Client client = clientRepo.findById(id).orElse(null);
        client.setNextDateContact(new Date(date*1000));
        return clientRepo.save(client);
    }
}
