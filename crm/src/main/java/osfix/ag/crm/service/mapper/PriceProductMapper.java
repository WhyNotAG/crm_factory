package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.product.PriceProduct;
import osfix.ag.crm.repo.product.PriceGroupProductRepo;
import osfix.ag.crm.service.dto.PriceProductDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceProductMapper implements EntityMapper<PriceProduct, PriceProductDTO> {
    @Autowired
    PriceGroupProductRepo priceGroupProductRepo;

    @Override
    public PriceProduct toEntity(PriceProductDTO dto) {
        PriceProduct priceProduct = new PriceProduct();
        priceProduct.setId(dto.getId());
        priceProduct.setCost(dto.getCost());
        priceProduct.setDealerPrice(dto.getDealerPrice());
        priceProduct.setDescription(dto.getDescription());
        priceProduct.setDistributorPrice(dto.getDistributorPrice());
        priceProduct.setLessThan1500Price(dto.getLessThan1500Price());
        priceProduct.setLessThan5000Price(dto.getLessThan5000Price());
        priceProduct.setName(dto.getName());
        priceProduct.setNumber(dto.getNumber());
        priceProduct.setPriceGroupProduct(priceGroupProductRepo.findById(dto.getPriceGroupProductId()).orElse(null));
        priceProduct.setRetailMarketPrice(dto.getRetailMarketPrice());
        priceProduct.setRetailPrice(dto.getRetailPrice());
        priceProduct.setUnits(dto.getUnits());
        priceProduct.setCost(dto.getCost());
        priceProduct.setPartnerPrice(dto.getPartnerPrice());
        return priceProduct;
    }

    @Override
    public PriceProductDTO fromEntity(PriceProduct entity) {
        PriceProductDTO dto = new PriceProductDTO();
        dto.setId(entity.getId());
        dto.setCost(entity.getCost());
        dto.setDealerPrice(entity.getDealerPrice());
        dto.setDescription(entity.getDescription());
        dto.setDistributorPrice(entity.getDistributorPrice());
        dto.setLessThan1500Price(entity.getLessThan1500Price());
        dto.setLessThan5000Price(entity.getLessThan5000Price());
        dto.setName(entity.getName());
        dto.setNumber(entity.getNumber());
        dto.setPriceGroupProductId(entity.getPriceGroupProduct().getId());
        dto.setRetailMarketPrice(entity.getRetailMarketPrice());
        dto.setRetailPrice(entity.getRetailPrice());
        dto.setUnits(entity.getUnits());
        dto.setCost(entity.getCost());
        dto.setPartnerPrice(entity.getPartnerPrice());
        return dto;
    }

    @Override
    public List<PriceProduct> fromDtoList(List<PriceProductDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PriceProductDTO> toDtoList(List<PriceProduct> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
