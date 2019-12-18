package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.product.RequestProduct;
import osfix.ag.crm.service.RequestProductService;
import osfix.ag.crm.service.dto.RequestProductDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/request_product")
public class RequestProductController {
    private RequestProductService requestProductService;

    public RequestProductController(RequestProductService requestProductService) {
        this.requestProductService = requestProductService;
    }

    @GetMapping("/")
    public ResponseEntity<List<RequestProduct>> findAll() {
        return ResponseEntity.ok().body(requestProductService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestProduct> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(requestProductService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<RequestProduct> add(@RequestBody RequestProductDTO requestProduct) {
        return ResponseEntity.ok().body(requestProductService.save(requestProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestProduct> update(@PathVariable(name = "id") Long id, @RequestBody RequestProductDTO requestProduct) {
        return ResponseEntity.ok().body(requestProductService.update(id, requestProduct));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        requestProductService.delete(id);
    }


}
