package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.factory.Packing;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.repo.factory.PackingRepo;
import osfix.ag.crm.service.ProductCategoryService;
import osfix.ag.crm.service.dto.ProductsDTO;
import osfix.ag.crm.service.dto.ProductsWithPackingsDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class ProductWithPackingsMapper implements EntityMapper<Product, ProductsWithPackingsDTO> {
    @Autowired
    ProductCategoryService productCategoryService;
    @Autowired
    PackingRepo packingRepo;

    @Override
    public Product toEntity(ProductsWithPackingsDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setWeight(dto.getWeight());
        product.setUnit(dto.getUnit());
        product.setComment(dto.getComment());
        product.setPackaging(dto.getPackaging());
        product.setName(dto.getName());
        product.setTypeOfProduct(dto.getTypeOfProduct());
        product.setPhoto(dto.getPhoto());
        product.setVendor(dto.getVendor());
        product.setProductionLocation(dto.getProductionLocation());

        if (productCategoryService.findByCategory(dto.getCategory()) != null) {
            product.setProductCategory(productCategoryService.findByCategory(dto.getCategory()));
        } else {product.setProductCategory(null);}


        if(dto.getPackings() != null) {
            for (Packing packing : dto.getPackings()) {
                product.getPackings().add(packingRepo.findById(packing.getId()).orElse(null));
            }
        }

        return product;
    }

    @Override
    public ProductsWithPackingsDTO fromEntity(Product entity) {
        ProductsWithPackingsDTO dto = new ProductsWithPackingsDTO();
        dto.setId(entity.getId());
        dto.setWeight(entity.getWeight());
        dto.setUnit(entity.getUnit());
        dto.setComment(entity.getComment());
        dto.setPackaging(entity.getPackaging());
        dto.setName(entity.getName());
        dto.setTypeOfProduct(entity.getTypeOfProduct());
        dto.setPhoto(entity.getPhoto());
        dto.setVendor(entity.getVendor());
        dto.setProductionLocation(entity.getProductionLocation());

        if(entity.getProductCategory() != null) {
            dto.setCategory(entity.getProductCategory().getCategory());
        } else { dto.setCategory(null);}

        if(entity.getPackings() != null) {
            List<Packing> packings = new ArrayList<>();
            for(Packing packing : entity.getPackings()) {
                packings.add(packing);
            }
            dto.setPackings(packings);
        }
        return dto;
    }

    @Override
    public List<Product> fromDtoList(List<ProductsWithPackingsDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsWithPackingsDTO> toDtoList(List<Product> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
