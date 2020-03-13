package osfix.ag.crm.service;
import osfix.ag.crm.domain.manager.Client;

import java.util.List;

public interface ClientService {
    List<Client> findAll();
    List<Client> findAllByCategory_Name(String name);
    List<Client> findAllByCategory_NameAndClientType(String name, String clientType);
    Client findById(Long id);
    Client update(Long id, Client client);
    Client save(Client client);
    void delete(Long id);
}
