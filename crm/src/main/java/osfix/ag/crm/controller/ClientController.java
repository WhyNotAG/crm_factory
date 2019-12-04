package osfix.ag.crm.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.Client;
import osfix.ag.crm.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    public ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public List<Client> getAllClients() { return clientService.findAll();}

    @GetMapping("/{id}")
    public Client getClient(@PathVariable(name="id") long id) {return clientService.findById(id);}

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping()
    public Client create(@RequestBody Client client) { //создание нового сообщения
        return clientService.save(client);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name="id") long id) { clientService.delete(id);}

    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @PutMapping("/{id}")
    public Client update(@PathVariable(name="id") Long id, @RequestBody Client client) {
        return clientService.update(id, client);
    }

}
