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
    public List<ClientDTO> getAllClients() { return clientMapper.toDtoList(clientService.findAll());}

    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable(name="id") long id) {return clientMapper.fromEntity(clientService.findById(id));}

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping()
    public ClientDTO create(@RequestBody ClientDTO client) {
        return clientMapper.fromEntity(clientService.save(clientMapper.toEntity(client)));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name="id") long id) { clientService.delete(id);}

    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @PutMapping("/{id}")
    public ClientDTO update(@PathVariable(name="id") Long id, @RequestBody ClientDTO client) {
        return clientMapper.fromEntity(clientService.update(id, clientMapper.toEntity(client)));
    }

    @PostMapping("/category/{name}")
    public List<ClientDTO> findAllByCategoryName(@PathVariable(name = "name") String name) {
        return clientMapper.toDtoList(clientService.findAllByCategory_Name(name));
    }

    @PostMapping("/category/{name}&{type}")
    public List<ClientDTO> findAllByCategoryName(@PathVariable(name = "name") String name,
                                              @PathVariable(name = "type") String type) {
        return clientMapper.toDtoList(clientService.findAllByCategory_NameAndClientType(name, type));
    }

}
