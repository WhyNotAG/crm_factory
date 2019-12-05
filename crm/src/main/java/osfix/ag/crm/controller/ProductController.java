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
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok().body(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping("/")
    public ResponseEntity<Product> add(@RequestBody ProductsDTO product) {
        return ResponseEntity.ok().body(productService.save(productMapper.toEntity(product)));
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable(name = "id") Long id, @RequestBody ProductsDTO product) {
        return ResponseEntity.ok().body(productService.update(id,productMapper.toEntity(product)));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        productService.delete(id);
    }
}
