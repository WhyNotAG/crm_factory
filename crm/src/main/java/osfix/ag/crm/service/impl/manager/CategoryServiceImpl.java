package osfix.ag.crm.service.impl.manager;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.manager.Category;
import osfix.ag.crm.repo.manager.CategoryRepo;
import osfix.ag.crm.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    @Override
    public Category update(Long id, Category category) {
        Category categoryFromDb = categoryRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(category, categoryFromDb, "id");
        return categoryRepo.save(categoryFromDb);
    }

    @Override
    public List<Category> findAllByTypeOrType(String type, String type2) {
        return categoryRepo.findAllByTypeOrType(type, type2);
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepo.deleteById(id);
    }
}
