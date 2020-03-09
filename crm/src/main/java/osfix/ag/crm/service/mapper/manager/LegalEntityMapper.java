package osfix.ag.crm.service.mapper.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.manager.LegalEntity;
import osfix.ag.crm.repo.manager.ClientRepo;
import osfix.ag.crm.service.dto.manager.LegalEntityDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LegalEntityMapper implements EntityMapper<LegalEntity, LegalEntityDTO> {
    @Autowired
    private ClientRepo clientRepo;

    @Override
    public LegalEntity toEntity(LegalEntityDTO dto) {
        LegalEntity legalEntity = new LegalEntity();
        legalEntity.setBik(dto.getBik());
        legalEntity.setCheckingAccount(dto.getCheckingAccount());
        legalEntity.setClient(clientRepo.findById(dto.getClientId()).orElse(null));
        legalEntity.setFactualAddress(dto.getFactualAddress());
        legalEntity.setId(dto.getId());
        legalEntity.setInn(dto.getInn());
        legalEntity.setKpp(dto.getKpp());
        legalEntity.setLegalAddress(dto.getLegalAddress());
        legalEntity.setOgrn(dto.getOgrn());
        return legalEntity;
    }

    @Override
    public LegalEntityDTO fromEntity(LegalEntity entity) {
        LegalEntityDTO dto = new LegalEntityDTO();
        dto.setBik(entity.getBik());
        dto.setCheckingAccount(entity.getCheckingAccount());
        dto.setClientId(entity.getClient().getId());
        dto.setFactualAddress(entity.getFactualAddress());
        dto.setId(entity.getId());
        dto.setInn(entity.getInn());
        dto.setKpp(entity.getKpp());
        dto.setLegalAddress(entity.getLegalAddress());
        dto.setOgrn(entity.getOgrn());
        return dto;
    }

    @Override
    public List<LegalEntity> fromDtoList(List<LegalEntityDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LegalEntityDTO> toDtoList(List<LegalEntity> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
