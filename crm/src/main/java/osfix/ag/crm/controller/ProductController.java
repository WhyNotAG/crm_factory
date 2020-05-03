package osfix.ag.crm.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.service.ProductService;
import osfix.ag.crm.service.dto.ProductsDTO;
import osfix.ag.crm.service.dto.ProductsWithoutPhotoDTO;
import osfix.ag.crm.service.mapper.ProductMapper;
import osfix.ag.crm.service.mapper.ProductWithoutPhotoMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private ProductService productService;
    private ProductMapper productMapper;
    private ProductWithoutPhotoMapper productWithoutPhotoMapper;

    public ProductController(ProductService productService, ProductMapper productMapper,
                             ProductWithoutPhotoMapper productWithoutPhotoMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.productWithoutPhotoMapper = productWithoutPhotoMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductsDTO>> findAll(){
        return ResponseEntity.ok().body(productMapper.toDtoList(productService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @PostMapping("/location/")
    public ResponseEntity<List<ProductsDTO>> findByLocation(@RequestBody ProductsDTO product) {
        return ResponseEntity.ok().body(productMapper.toDtoList(productService.findByProductLocation(product.getProductionLocation())));
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

    @PostMapping("/category/")
    public ResponseEntity<List<ProductsWithoutPhotoDTO>> findByCategory(@RequestBody ProductsDTO products) {
        return ResponseEntity.ok().body(productWithoutPhotoMapper.toDtoList(productService.findByCategory(products.getCategory())));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        productService.delete(id);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<ProductsDTO> addPackings(@PathVariable(name = "id") Long id, @RequestBody ProductsDTO productsDTO) {
        return ResponseEntity.ok().body(productMapper.fromEntity(productService.addPacking(id, productsDTO.getPackings())));
    }
}
