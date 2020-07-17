package osfix.ag.crm.controller.manager;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.manager.Category;
import osfix.ag.crm.service.CategoryService;
import osfix.ag.crm.service.dto.manager.CategoryDTO;
import osfix.ag.crm.service.mapper.manager.CategoryMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/")
    public List<Category> getAll() { return categoryService.findAll();}

    @GetMapping("/{id}")
    public Category getById(@PathVariable(name="id") long id) {return categoryService.findById(id);}

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping()
    public Category create(@RequestBody CategoryDTO category) {
        return categoryService.save(categoryMapper.toEntity(category));
    }

    @GetMapping("/client/")
    public List<Category> getClientCategories(){
        return categoryService.findAllByTypeOrType("client", null);
    }

    @GetMapping("/supplier/")
    public List<Category> getSupplierCategories(){
        return categoryService.findAllByTypeOrType("supplier", "supplier");
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name="id") long id) { categoryService.delete(id);}

    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @PutMapping("/{id}")
    public Category update(@PathVariable(name="id") Long id, @RequestBody CategoryDTO category) {
        return categoryService.update(id,categoryMapper.toEntity(category));
    }
}
