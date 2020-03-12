package osfix.ag.crm.service.impl.manager;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.manager.Client;
import osfix.ag.crm.repo.manager.ClientRepo;
import osfix.ag.crm.service.ClientService;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepo clientRepo;

    public ClientServiceImpl(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public List<Client> findAll() {
        return clientRepo.findAll();
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
}