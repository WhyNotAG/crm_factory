package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.product.ProductCategory;
import osfix.ag.crm.service.ProductCategoryService;

import java.util.List;

@RestController
@RequestMapping("api/v1/product_category")
public class ProductCategoryController {
    private ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductCategory>> findAll() {
       return ResponseEntity.ok().body(productCategoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(productCategoryService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<ProductCategory> add(@RequestBody ProductCategory productCategory) {
        return ResponseEntity.ok().body(productCategoryService.save(productCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> update(@PathVariable(name = "id") Long id, @RequestBody ProductCategory productCategory) {
        return ResponseEntity.ok().body(productCategoryService.update(id, productCategory));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        productCategoryService.delete(id);
    }
}
