package osfix.ag.crm.service.mapper.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.manager.History;
import osfix.ag.crm.repo.manager.ClientRepo;
import osfix.ag.crm.service.dto.manager.HistoryDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HistoryMapper implements EntityMapper<History, HistoryDTO> {
    @Autowired
    private ClientRepo clientRepo;

    @Override
    public History toEntity(HistoryDTO dto) {
        History history = new History();
        history.setAction(dto.getAction());
        history.setClient(clientRepo.findById(dto.getClientId()).orElse(null));
        history.setComment(dto.getComment());
        history.setDate(dto.getDate());
        history.setId(dto.getId());
        history.setResult(dto.getResult());
        return history;
    }

    @Override
    public HistoryDTO fromEntity(History entity) {
        HistoryDTO dto = new HistoryDTO();
        dto.setAction(dto.getAction());
        dto.setClientId(entity.getClient().getId());
        dto.setComment(dto.getComment());
        dto.setDate(dto.getDate());
        dto.setId(dto.getId());
        dto.setResult(dto.getResult());
        return dto;
    }

    @Override
    public List<History> fromDtoList(List<HistoryDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<HistoryDTO> toDtoList(List<History> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
