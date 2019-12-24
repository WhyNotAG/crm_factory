package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.product.LemzProduct;
import osfix.ag.crm.service.LemzProductService;
import osfix.ag.crm.service.dto.RequestProductDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lemz_product")
public class LemzProductController {
    private LemzProductService lemzProductService;

    public LemzProductController(LemzProductService lemzProductService) {
        this.lemzProductService = lemzProductService;
    }

    @GetMapping("/")
    public ResponseEntity<List<LemzProduct>> findAll() {
        return ResponseEntity.ok().body(lemzProductService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LemzProduct> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(lemzProductService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<LemzProduct> add(@RequestBody RequestProductDTO requestProduct) {
        return ResponseEntity.ok().body(lemzProductService.save(requestProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LemzProduct> update(@PathVariable(name = "id") Long id, @RequestBody RequestProductDTO requestProduct) {
        return ResponseEntity.ok().body(lemzProductService.update(id, requestProduct));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        lemzProductService.delete(id);
    }
}
