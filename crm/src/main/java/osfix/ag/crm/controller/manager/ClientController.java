package osfix.ag.crm.controller.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.manager.Client;
import osfix.ag.crm.service.ClientService;
import osfix.ag.crm.service.dto.manager.ClientDTO;
import osfix.ag.crm.service.mapper.manager.ClientMapper;

import java.util.List;
import java.util.Set;

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
    public Page<Client> getAllClients(@PageableDefault(sort = {"name"}, direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
        Page<Client> clients = clientService.findAll(pageable);
        System.out.println(clients.getTotalPages());
        return clients;
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable(name="id") long id) {return clientService.findById(id);}

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping()
    public Client create(@RequestBody ClientDTO client) {
        return clientService.save(client);
    }


    @PostMapping("/search/")
    public ResponseEntity<Set<Client>> search(@RequestBody ClientDTO client) {
        return ResponseEntity.ok().body(clientService.search(client.getName(), client.getType()));
    }

    @PostMapping("/search/city&taxes/")
    public ResponseEntity<Set<Client>> searchByCity(@RequestBody ClientDTO client) {
        return ResponseEntity.ok().body(clientService.findByCity(client.getCity(), client.getTaxes(), client.getType()));
    }

    @PostMapping("/search/city/")
    public ResponseEntity<Set<Client>> searchByCityWithoutTaxes(@RequestBody ClientDTO client) {
        return ResponseEntity.ok().body(clientService.findByCityWithoutTaxes(client.getCity(), client.getType()));
    }


    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name="id") long id) { clientService.delete(id);}

    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @PutMapping("/{id}")
    public Client update(@PathVariable(name="id") Long id, @RequestBody ClientDTO client) {
        System.out.println(client.getId());
        return clientService.update(id, client);
    }

    @PostMapping("/category/")
    public Page<Client> findAllByCategoryName(@RequestBody ClientDTO client, @PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC, size = 20) Pageable pageable) {
        return clientService.findAllByCategory_Name(client.getCategoryName(), pageable);
    }

    @PostMapping("/category_type/")
    public Page<Client> findAllByCategoryNameAndType(@RequestBody ClientDTO client, @PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC, size = 20) Pageable pageable) {
        return clientService.findAllByCategory_NameAndClientType(client.getCategoryName(), client.getClientType(), pageable);
    }

    @PostMapping("/category_type/client/")
    public Page<Client> findAllClientByCategoryNameAndType(@RequestBody ClientDTO client, @PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC, size = 20) Pageable pageable) {
        return clientService.findAllByCategory_NameAndClientTypeAndTypeOrType(client.getCategoryName(), client.getClientType(), null, null, pageable);
    }

    @PostMapping("/category_type/supplier/")
    public Page<Client> findAllSupplierByCategoryNameAndType(@RequestBody ClientDTO client, @PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC, size = 20) Pageable pageable) {
        return clientService.findAllByCategory_NameAndClientTypeAndTypeOrType(client.getCategoryName(), client.getClientType(), "supplier", "supplier", pageable);
    }

    @PostMapping("/date/")
    public Client changeDate(@RequestBody ClientDTO client) {
        return clientService.updateDate(client.getId(), client.getNextDateContact());
    }


    @Secured({"ROLE_ADMIN"})
    @GetMapping("/set_close/{id}")
    public Client setClosed(@PathVariable(name = "id") Long id) {
        return  null;
    }
}
