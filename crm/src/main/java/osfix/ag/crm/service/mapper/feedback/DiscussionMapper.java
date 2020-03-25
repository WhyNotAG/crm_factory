package osfix.ag.crm.service.mapper.feedback;

import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.feedback.Discussion;
import osfix.ag.crm.service.dto.feedback.DiscussionDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiscussionMapper implements EntityMapper<Discussion, DiscussionDTO> {
    @Override
    public Discussion toEntity(DiscussionDTO dto) {
        Discussion discussion = new Discussion();
        discussion.setAuthor(dto.getAuthor());
        discussion.setDate(new Date(dto.getDate()*1000));
        discussion.setId(dto.getId());
        discussion.setAuthor(dto.getAuthor());
        discussion.setStatus(dto.getStatus());
        discussion.setSubject(dto.getSubject());
        discussion.setText(dto.getText());
        return discussion;
    }

    @Override
    public DiscussionDTO fromEntity(Discussion entity) {
        DiscussionDTO dto = new DiscussionDTO();
        dto.setAuthor(entity.getAuthor());
        dto.setDate(entity.getDate().getTime());
        dto.setId(entity.getId());
        dto.setAuthor(entity.getAuthor());
        dto.setStatus(entity.getStatus());
        dto.setSubject(entity.getSubject());
        dto.setText(entity.getText());
        return dto;
    }

    @Override
    public List<Discussion> fromDtoList(List<DiscussionDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscussionDTO> toDtoList(List<Discussion> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
