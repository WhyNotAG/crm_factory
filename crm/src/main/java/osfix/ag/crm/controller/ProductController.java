package osfix.ag.crm.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.service.ProductService;
import osfix.ag.crm.service.dto.ProductsDTO;
import osfix.ag.crm.service.mapper.ProductMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private ProductService productService;
    private ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductsDTO>> findAll(){
        return ResponseEntity.ok().body(productMapper.toDtoList(productService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(productMapper.fromEntity(productService.findById(id)));
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping("/")
    public ResponseEntity<ProductsDTO> add(@RequestBody ProductsDTO product) {
        return ResponseEntity.ok().body(productMapper.fromEntity(productService.save(productMapper.toEntity(product))));
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PutMapping("/{id}")
    public ResponseEntity<ProductsDTO> update(@PathVariable(name = "id") Long id, @RequestBody ProductsDTO product) {
        return ResponseEntity.ok().body(productMapper.fromEntity(productService.update(id,productMapper.toEntity(product))));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductsDTO>> findByCategory(@PathVariable(name = "category") String category) {
        return ResponseEntity.ok().body(productMapper.toDtoList(productService.findByCategory(category)));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        productService.delete(id);
    }
}
