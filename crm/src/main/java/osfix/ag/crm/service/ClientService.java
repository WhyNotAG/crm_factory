package osfix.ag.crm.service;
import osfix.ag.crm.domain.Client;
import java.util.List;

public interface ClientService {
    List<Client> findAll();
    Client findById(Long id);
    Client update(Long id, Client client);
    Client save(Client client);
    void delete(Long id);
}
