package osfix.ag.crm.service.mapper.manager;

import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.manager.Category;
import osfix.ag.crm.service.dto.manager.CategoryDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper implements EntityMapper<Category, CategoryDTO> {
    @Override
    public Category toEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setVisibility(dto.getVisibility());
        return category;
    }

    @Override
    public CategoryDTO fromEntity(Category entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setVisibility(entity.getVisibility());
        return dto;
    }

    @Override
    public List<Category> fromDtoList(List<CategoryDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> toDtoList(List<Category> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
