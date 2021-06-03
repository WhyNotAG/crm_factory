package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.service.ProductCategoryService;
import osfix.ag.crm.service.dto.ProductsWithoutPhotoDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductWithoutPhotoMapper implements EntityMapper<Product, ProductsWithoutPhotoDTO> {

    @Autowired
    ProductCategoryService productCategoryService;

    @Override
    public Product toEntity(ProductsWithoutPhotoDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setWeight(dto.getWeight());
        product.setUnit(dto.getUnit());
        product.setComment(dto.getComment());
        //product.setPackaging(dto.getPackaging());
        product.setName(dto.getName());
        product.setTypeOfProduct(dto.getTypeOfProduct());
        product.setVendor(dto.getVendor());
        product.setProductionLocation(dto.getProductionLocation());
        product.setBarcode(dto.getBarcode());
        product.setDescription(dto.getDescription());

        if (productCategoryService.findByCategory(dto.getCategory()) != null) {
            product.setProductCategory(productCategoryService.findByCategory(dto.getCategory()));
        } else {product.setProductCategory(null);}

        return product;
    }

    @Override
    public ProductsWithoutPhotoDTO fromEntity(Product entity) {
        ProductsWithoutPhotoDTO dto = new ProductsWithoutPhotoDTO();
        dto.setId(entity.getId());
        dto.setWeight(entity.getWeight());
        dto.setUnit(entity.getUnit());
        dto.setComment(entity.getComment());
        //dto.setPackaging(entity.getPackaging());
        dto.setName(entity.getName());
        dto.setTypeOfProduct(entity.getTypeOfProduct());
        dto.setVendor(entity.getVendor());
        dto.setProductionLocation(entity.getProductionLocation());
        dto.setBarcode(entity.getBarcode());
        dto.setDescription(entity.getDescription());
        if(entity.getProductCategory() != null) {
            dto.setCategory(entity.getProductCategory().getCategory());
        } else { dto.setCategory(null);}

        return dto;
    }

    @Override
    public List<Product> fromDtoList(List<ProductsWithoutPhotoDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsWithoutPhotoDTO> toDtoList(List<Product> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
