package osfix.ag.crm.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import osfix.ag.crm.domain.manager.Client;
import osfix.ag.crm.service.dto.manager.ClientDTO;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ClientService {
    Page<Client> findAll(Pageable pageable);
    Page<Client> findAllByCategory_Name(String name, Pageable pageable);
    Page<Client> findAllByCategory_NameAndClientType(String name, String clientType, Pageable pageable);
    Page<Client> findAllByCategory_NameAndClientTypeAndTypeOrType(String name, String clientType, String type, String type2, Pageable pageable);
    Set<Client> search(String substring, String type);
    Set<Client> findByCity(String substring, Boolean taxes, String type);
    Set<Client> findByCityWithoutTaxes(String substring, String type);
    Page<Client> findAllByCategory_NameAndClientTypeAndTypeIn(List<String> name, String clientType, String type, Pageable pageable);
    Client findById(Long id);
    Client update(Long id, ClientDTO client);
    Client save(ClientDTO client);
    Client updateDate(Long id, Long date);
    void delete(Long id);
}
