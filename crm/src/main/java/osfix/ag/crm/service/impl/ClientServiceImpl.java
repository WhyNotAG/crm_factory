package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import osfix.ag.crm.domain.Client;
import osfix.ag.crm.repo.ClientRepo;
import osfix.ag.crm.service.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private ClientRepo clientRepo;

    @Override
    public List<Client> findAll() {
        return clientRepo.findAll();
    }

    @Override
    public Client findById(Long id) {
       return findId(id);
    }

    @Override
    public void delete(Long id) {
        clientRepo.deleteById(id);
    }

    @Override
    public Client update(Long id, Client client) {
        Client clientFromDb = findId(id);
        BeanUtils.copyProperties(client,clientFromDb, "id");
        return client;
    }

    public Client findId(Long id) {
        Client client = clientRepo.findById(id).orElse(null);
        if (client == null) {
            return null;
        } else {
            return client;
        }
    }
}
