package osfix.ag.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.Client;
import osfix.ag.crm.repo.ClientRepo;
import osfix.ag.crm.service.ClientService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    public ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() { return clientService.findAll();}

    @GetMapping("/{id}")
    public Client getClient(@PathVariable(name="id") long id) {return clientService.findById(id);}

    @PostMapping()
    public Client create(@RequestBody Client client) { //создание нового сообщения
        return clientService.save(client);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name="id") long id) { clientService.delete(id);}

    @PutMapping("/{id}")
    public Client update(@PathVariable(name="id") long id, @RequestBody Client client) {
        return clientService.update(id, client);
    }

}
