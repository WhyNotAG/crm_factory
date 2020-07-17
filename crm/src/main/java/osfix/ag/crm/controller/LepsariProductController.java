package osfix.ag.crm.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.product.LepsariProduct;
import osfix.ag.crm.service.LepsariProductService;
import osfix.ag.crm.service.dto.RequestProductDTO;
import osfix.ag.crm.service.mapper.LepsariProductMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lepsari_product")
public class LepsariProductController {
    private LepsariProductService lepsariProductService;
    private LepsariProductMapper lepsariProductMapper;

    public LepsariProductController(LepsariProductService lepsariProductService,
                                    LepsariProductMapper lepsariProductMapper) {
        this.lepsariProductService = lepsariProductService;
        this.lepsariProductMapper = lepsariProductMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<LepsariProduct>> findAll() {
        return ResponseEntity.ok().body(lepsariProductService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LepsariProduct> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(lepsariProductService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<LepsariProduct> add(@RequestBody RequestProductDTO requestProduct) {
        return ResponseEntity.ok().body(lepsariProductService.save(lepsariProductMapper.toEntity(requestProduct)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LepsariProduct> update(@PathVariable(name = "id") Long id, @RequestBody RequestProductDTO requestProduct) {
        return ResponseEntity.ok().body(lepsariProductService.update(id, requestProduct));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<LepsariProduct> changeStatus(@PathVariable(name = "id") Long id, @RequestBody RequestProductDTO requestProductDTO) {
        return ResponseEntity.ok().body(lepsariProductService.changeStatus(id, requestProductDTO.getStatus()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        lepsariProductService.delete(id);
    }
}
