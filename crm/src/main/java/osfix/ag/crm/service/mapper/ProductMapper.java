package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.factory.Packing;
import osfix.ag.crm.domain.product.Product;
import osfix.ag.crm.repo.factory.PackingRepo;
import osfix.ag.crm.repo.product.ProductCategoryRepo;
import osfix.ag.crm.service.ProductCategoryService;
import osfix.ag.crm.service.dto.ProductsDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper implements EntityMapper<Product, ProductsDTO> {

    @Autowired
    ProductCategoryService productCategoryService;
    @Autowired
    PackingRepo packingRepo;

    @Override
    public Product toEntity(ProductsDTO dto) {
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
        product.setBarcode(dto.getBarcode());
        product.setDescription(dto.getDescription());

        if (productCategoryService.findByCategory(dto.getCategory()) != null) {
            product.setProductCategory(productCategoryService.findByCategory(dto.getCategory()));
        } else {product.setProductCategory(null);}


//        if(dto.getPackings() != null) {
//            for (Long packing : dto.getPackings()) {
//                product.getPackings().add(packingRepo.findById(packing).orElse(null));
//            }
//        }

        return product;
    }

    @Override
    public ProductsDTO fromEntity(Product entity) {
        ProductsDTO dto = new ProductsDTO();
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
        dto.setBarcode(entity.getBarcode());
        dto.setDescription(entity.getDescription());

        if(entity.getProductCategory() != null) {
            dto.setCategory(entity.getProductCategory().getCategory());
        } else { dto.setCategory(null);}

//        if(entity.getPackings() != null) {
//            List<Long> packings = new ArrayList<>();
//            for(Packing packing : entity.getPackings()) {
//                packings.add(packing.getId());
//            }
//            dto.setPackings(packings);
//        }
        return dto;
    }

    @Override
    public List<Product> fromDtoList(List<ProductsDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsDTO> toDtoList(List<Product> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
