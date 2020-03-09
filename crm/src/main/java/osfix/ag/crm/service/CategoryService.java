package osfix.ag.crm.service;

import osfix.ag.crm.domain.manager.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
    Category update(Long id, Category category);
    Category save(Category category);
    void delete(Long id);
}
