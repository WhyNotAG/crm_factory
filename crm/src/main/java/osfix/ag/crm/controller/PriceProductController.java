package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.product.PriceProduct;
import osfix.ag.crm.service.PriceProductService;

import java.util.List;

@RequestMapping("/api/v1/price_product")
@RestController
public class PriceProductController {
    PriceProductService priceProductService;

    public PriceProductController(PriceProductService priceProductService) {
        this.priceProductService = priceProductService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PriceProduct>> findAll() {
        return ResponseEntity.ok().body(priceProductService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceProduct> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(priceProductService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<PriceProduct> add(@RequestBody PriceProduct priceProduct) {
        return ResponseEntity.ok().body(priceProductService.save(priceProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PriceProduct> update(@PathVariable(name = "id") Long id, @RequestBody PriceProduct priceProduct) {
        return ResponseEntity.ok().body(priceProductService.update(id, priceProduct));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        priceProductService.delete(id);
    }
}
