package osfix.ag.crm.service.mapper;

import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.product.ProductCategory;
import osfix.ag.crm.service.dto.ProductsCategoryDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductsCategoryMapper implements EntityMapper<ProductCategory, ProductsCategoryDTO> {
    @Override
    public ProductCategory toEntity(ProductsCategoryDTO dto) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategory(dto.getCategory());
        productCategory.setId(dto.getId());
        return productCategory;
    }

    @Override
    public ProductsCategoryDTO fromEntity(ProductCategory entity) {
        ProductsCategoryDTO productsCategoryDTO = new ProductsCategoryDTO();
        productsCategoryDTO.setId(entity.getId());
        productsCategoryDTO.setCategory(entity.getCategory());
        return productsCategoryDTO;
    }

    @Override
    public List<ProductCategory> fromDtoList(List<ProductsCategoryDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsCategoryDTO> toDtoList(List<ProductCategory> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
