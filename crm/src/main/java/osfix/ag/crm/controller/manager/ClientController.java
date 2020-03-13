package osfix.ag.crm.controller.manager;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.manager.Client;
import osfix.ag.crm.service.ClientService;
import osfix.ag.crm.service.dto.manager.ClientDTO;
import osfix.ag.crm.service.mapper.manager.ClientMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    public ClientService clientService;
    private ClientMapper clientMapper;

    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping("/")
    public List<Client> getAllClients() { return clientService.findAll();}

    @GetMapping("/{id}")
    public Client getClient(@PathVariable(name="id") long id) {return clientService.findById(id);}

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping()
    public Client create(@RequestBody ClientDTO client) {
        return clientService.save(clientMapper.toEntity(client));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name="id") long id) { clientService.delete(id);}

    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @PutMapping("/{id}")
    public Client update(@PathVariable(name="id") Long id, @RequestBody ClientDTO client) {
        return clientService.update(id, clientMapper.toEntity(client));
    }

    @PostMapping("/category/")
    public List<Client> findAllByCategoryName(@RequestBody ClientDTO client) {
        return clientService.findAllByCategory_Name(client.getCategoryName());
    }

    @PostMapping("/category_type/")
    public List<Client> findAllByCategoryNameAndType(@RequestBody ClientDTO client) {
        return clientService.findAllByCategory_NameAndClientType(client.getCategoryName(), client.getClientType());
    }

    @PostMapping("/date/")
    public Client changeDate(@RequestBody ClientDTO client) {
        Client client1 = clientMapper.toEntity(client);
        return clientService.updateDate(client1.getId(), client1.getNextDateContact());
    }


}
