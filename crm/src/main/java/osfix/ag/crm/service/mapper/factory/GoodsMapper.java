package osfix.ag.crm.service.mapper.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.factory.Goods;
import osfix.ag.crm.repo.factory.PackingRepo;
import osfix.ag.crm.repo.product.ProductRepo;
import osfix.ag.crm.service.dto.factory.GoodsDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GoodsMapper implements EntityMapper<Goods, GoodsDTO> {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    PackingRepo packingRepo;

    @Override
    public Goods toEntity(GoodsDTO dto) {
        Goods goods = new Goods();
        goods.setId(dto.getId());
        goods.setBarcode(dto.getBarcode());
        goods.setPacking(packingRepo.findById(dto.getPackingId()).orElse(null));
        goods.setProduct(productRepo.findById(dto.getProductId()).orElse(null));
        goods.setQuantity(dto.getQuantity());
        goods.setUnit(dto.getUnit());
        return goods;
    }

    @Override
    public GoodsDTO fromEntity(Goods entity) {
        GoodsDTO dto = new GoodsDTO();
        dto.setBarcode(entity.getBarcode());
        dto.setId(dto.getId());
        dto.setPackingId(entity.getPacking().getId());
        dto.setProductId(entity.getProduct().getId());
        dto.setQuantity(entity.getQuantity());
        dto.setUnit(entity.getUnit());
        return dto;
    }

    @Override
    public List<Goods> fromDtoList(List<GoodsDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<GoodsDTO> toDtoList(List<Goods> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
