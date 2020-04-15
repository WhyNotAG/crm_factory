package osfix.ag.crm.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import osfix.ag.crm.domain.manager.Client;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ClientService {
    Page<Client> findAll(Pageable pageable);
    Page<Client> findAllByCategory_Name(String name, Pageable pageable);
    Page<Client> findAllByCategory_NameAndClientType(String name, String clientType, Pageable pageable);
    Set<Client> search(String substring);
    Client findById(Long id);
    Client update(Long id, Client client);
    Client save(Client client);
    Client updateDate(Long id, Long date);
    void delete(Long id);
}
