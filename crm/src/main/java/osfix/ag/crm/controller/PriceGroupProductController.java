package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.product.PriceGroupProduct;
import osfix.ag.crm.service.PriceGroupProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/price_group")
public class PriceGroupProductController {
    PriceGroupProductService priceGroupProductService;

    public PriceGroupProductController(PriceGroupProductService priceGroupProductService) {
        this.priceGroupProductService = priceGroupProductService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PriceGroupProduct>> findAll() {
        return ResponseEntity.ok().body(priceGroupProductService.findAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PriceGroupProduct> findByName(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok().body(priceGroupProductService.findByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceGroupProduct> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(priceGroupProductService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<PriceGroupProduct> add(@RequestBody PriceGroupProduct priceGroupProduct) {
        return ResponseEntity.ok().body(priceGroupProductService.save(priceGroupProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PriceGroupProduct> update(@PathVariable(name = "id") Long id,
                                                    @RequestBody PriceGroupProduct priceGroupProduct) {
        return ResponseEntity.ok().body(priceGroupProductService.update(id, priceGroupProduct));
    }

    @PutMapping("/name/{name}")
    public ResponseEntity<PriceGroupProduct> updateByName(@PathVariable(name = "name") String  name,
                                                    @RequestBody PriceGroupProduct priceGroupProduct) {
        return ResponseEntity.ok().body(priceGroupProductService.update(name, priceGroupProduct));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        priceGroupProductService.delete(id);
    }
}
