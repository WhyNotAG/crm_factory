package osfix.ag.crm.service.mapper.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.factory.Goods;
import osfix.ag.crm.service.dto.factory.GoodsViewDTO;
import osfix.ag.crm.service.mapper.EntityMapper;
import osfix.ag.crm.service.mapper.ProductWithoutPhotoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GoodsViewMapper implements EntityMapper<Goods, GoodsViewDTO> {

    @Autowired
    ProductWithoutPhotoMapper productMapper;


    @Override
    public Goods toEntity(GoodsViewDTO dto) {
        Goods goods = new Goods();
        goods.setId(dto.getId());
        goods.setBarcode(dto.getBarcode());
        goods.setProduct(productMapper.toEntity(dto.getProduct()));
        goods.setPacking(dto.getPacking());
        goods.setQuantity(dto.getQuantity());
        goods.setUnit(dto.getUnit());
        return goods;
    }

    @Override
    public GoodsViewDTO fromEntity(Goods entity) {
        GoodsViewDTO dto = new GoodsViewDTO();
        dto.setBarcode(entity.getBarcode());
        dto.setId(entity.getId());
        dto.setPacking(entity.getPacking());
        dto.setProduct(productMapper.fromEntity(entity.getProduct()));
        dto.setQuantity(entity.getQuantity());
        dto.setUnit(entity.getUnit());
        return dto;
    }

    @Override
    public List<Goods> fromDtoList(List<GoodsViewDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<GoodsViewDTO> toDtoList(List<Goods> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
