package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.service.RequestService;
import osfix.ag.crm.service.dto.request.AddProductsDTO;
import osfix.ag.crm.service.dto.request.RequestDTO;
import osfix.ag.crm.service.dto.request.RequestViewDTO;
import osfix.ag.crm.service.mapper.RequestMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/request")
public class RequestController {
    private RequestService requestService;
    private RequestMapper requestMapper;

    public RequestController(RequestService requestService, RequestMapper requestMapper) {
        this.requestService = requestService;
        this.requestMapper = requestMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<RequestViewDTO>> getAllClients() {
        return ResponseEntity.ok().body(requestService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestViewDTO> getClient(@PathVariable(name = "id") long id) {
        return  ResponseEntity.ok().body(requestService.findById(id));
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping()
    public ResponseEntity<RequestViewDTO> create(@RequestBody RequestDTO request) {
        RequestViewDTO result = requestService.save(requestMapper.toEntity(request));
        return ResponseEntity.ok().body(result);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PutMapping("/{id}")
    public ResponseEntity<RequestViewDTO> update(@PathVariable(name = "id") Long id, @RequestBody RequestDTO request) {
        RequestViewDTO result =  requestService.update(id, requestMapper.toEntity(request));
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/status/{id}")
    public void changeStatus(@PathVariable(name = "id") Long id, @RequestBody RequestDTO request) {
        requestService.changeStatus(id, request.getStatus());
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long id) { requestService.delete(id);}

    @PostMapping("/{id}")
    public ResponseEntity<RequestViewDTO> addProducts(@PathVariable(name = "id") Long id, @RequestBody AddProductsDTO addProductsDTO) {
        RequestViewDTO request = requestService.addProduct(id, addProductsDTO.getProductsName(), addProductsDTO.getQuantity(), addProductsDTO.getPackaging());
        return ResponseEntity.ok().body(request);
    }

    @GetMapping("/transfer/{id}/{factory}")
    public ResponseEntity<RequestViewDTO> transfer(@PathVariable(name = "id") Long id, @PathVariable(name = "factory") String factory) {
        return ResponseEntity.ok().body(requestService.copy(id,factory));
    }

    @GetMapping("/factory/{factory}")
    public ResponseEntity<List<RequestViewDTO>> findByFactory(@PathVariable(name = "factory") String factory) {
        return ResponseEntity.ok().body(requestService.findByFactory(factory));
    }

    @DeleteMapping("/pro/{id}")
    public void deletePro(@PathVariable(name = "id") Long id) {
        requestService.deletePro(id);
    }

    @GetMapping("/addClient/{request}/{client}/")
    public ResponseEntity<RequestViewDTO> addClient(@PathVariable(name = "client") Long clientId, @PathVariable(name = "request") Long requestId){
        return ResponseEntity.ok().body(requestService.addClient(requestId, clientId));
    }

}
