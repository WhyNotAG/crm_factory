package osfix.ag.crm.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.LEMZ;
import osfix.ag.crm.repo.LEMZRepo;
import osfix.ag.crm.service.LEMZService;
import osfix.ag.crm.service.dto.request.FactoryDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LemzMapper implements EntityMapper <LEMZ, FactoryDTO> {

    public LemzMapper() {
        super();
    }

    @Autowired
    private LEMZService lemzService;

    @Override
    public LEMZ toEntity(FactoryDTO dto) {
        LEMZ lemz = new LEMZ();
        lemz.setStatus(dto.getStatus());
        lemz.setId(dto.getId());
        lemz.setShippingDate(dto.getShippingDate());
        lemz.setQuantity(dto.getQuantity());
        lemz.setDate(dto.getDate());
        lemz.setComment(dto.getComment());
        lemz.setCodeWord(dto.getCodeWord());
        lemz.setResponsible(dto.getResponsible());
        return lemz;
    }

    @Override
    public FactoryDTO fromEntity(LEMZ entity) {
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
    public List<LEMZ> fromDtoList(List<FactoryDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<FactoryDTO> toDtoList(List<LEMZ> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
