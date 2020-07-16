package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.LEMZ;
import osfix.ag.crm.domain.Lepsari;
import osfix.ag.crm.service.LEMZService;
import osfix.ag.crm.service.dto.request.FactoryDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LepsariMapper implements EntityMapper <Lepsari, FactoryDTO> {

    @Autowired
    private LEMZService lemzService;

    @Override
    public Lepsari toEntity(FactoryDTO dto) {
        Lepsari lepsari = new Lepsari();
        lepsari.setStatus(dto.getStatus());
        lepsari.setId(dto.getId());
        lepsari.setShippingDate(dto.getShippingDate());
        lepsari.setQuantity(dto.getQuantity());
        lepsari.setDate(dto.getDate());
        lepsari.setComment(dto.getComment());
        lepsari.setCodeWord(dto.getCodeWord());
        lepsari.setResponsible(dto.getResponsible());
        return lepsari;
    }

    @Override
    public FactoryDTO fromEntity(Lepsari entity) {
        FactoryDTO dto = new FactoryDTO();
        dto.setStatus(entity.getStatus());
        dto.setId(entity.getId());
        dto.setShippingDate(entity.getShippingDate());
        dto.setQuantity(entity.getQuantity());
        dto.setDate(entity.getDate());
        dto.setComment(entity.getComment());
        dto.setCodeWord(entity.getCodeWord());
        dto.setResponsible(entity.getResponsible());
        return dto;
    }

    @Override
    public List<Lepsari> fromDtoList(List<FactoryDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<FactoryDTO> toDtoList(List<Lepsari> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
