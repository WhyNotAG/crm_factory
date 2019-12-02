package osfix.ag.crm.controller;

import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.Request;
import osfix.ag.crm.service.RequestService;
import osfix.ag.crm.service.impl.RequestServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/request")
public class RequestController {
    private RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/")
    public List<Request> getAllClients() { return requestService.findAll(); }

    @GetMapping("/{id}")
    public Request getClient(@PathVariable(name = "id") long id) {return  requestService.findById(id); }

    @PostMapping()
    public Request create(@RequestBody Request request) {return requestService.save(request); }

    @PutMapping("/{id}")
    public Request update(@PathVariable(name = "id") Long id, @RequestBody Request request) {
        return requestService.update(id, request);
    }

    @PutMapping("/status/{id}")
    public void changeStatus(@PathVariable(name = "id") Long id, @RequestBody Request request) {
        requestService.changeStatus(id, request.getStatus());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long id) { requestService.delete(id);}


}
