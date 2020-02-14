package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.product.PriceProduct;
import osfix.ag.crm.service.PriceProductService;
import osfix.ag.crm.service.dto.PriceDTO;
import osfix.ag.crm.service.dto.PriceProductDTO;
import osfix.ag.crm.service.mapper.PriceProductMapper;

import java.util.List;

@RequestMapping("/api/v1/price_product")
@RestController
public class PriceProductController {
    PriceProductService priceProductService;
    PriceProductMapper priceProductMapper;

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
    public ResponseEntity<PriceProduct> add(@RequestBody PriceProductDTO priceProduct) {
        return ResponseEntity.ok().body(priceProductService.save(priceProductMapper.toEntity(priceProduct)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PriceProduct> update(@PathVariable(name = "id") Long id, @RequestBody PriceProductDTO priceProduct) {
        return ResponseEntity.ok().body(priceProductService.update(id, priceProductMapper.toEntity(priceProduct)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        priceProductService.delete(id);
    }

    @GetMapping("/price")
    public PriceDTO getPrice() {
        return priceProductService.getPrice();
    }

    @PostMapping("/price")
    public void setPrice(@RequestBody PriceDTO priceDTO) {
        priceProductService.setPrice(priceDTO);
    }
}
