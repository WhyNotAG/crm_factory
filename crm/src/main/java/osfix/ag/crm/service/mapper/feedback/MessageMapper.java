package osfix.ag.crm.service.mapper.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.feedback.Message;
import osfix.ag.crm.repo.feedback.DiscussionRepo;
import osfix.ag.crm.service.dto.feedback.MessageDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper implements EntityMapper<Message, MessageDTO> {
    @Autowired
    DiscussionRepo discussionRepo;

    @Override
    public Message toEntity(MessageDTO dto) {
        Message message = new Message();
        message.setAuthor(dto.getAuthor());
        message.setDate(new Date(dto.getDate()*1000));
        message.setDiscussion(discussionRepo.findById(dto.getDiscussionId()).orElse(null));
        message.setId(dto.getId());
        message.setText(dto.getText());
        return message;
    }

    @Override
    public MessageDTO fromEntity(Message entity) {
        MessageDTO dto = new MessageDTO();
        dto.setAuthor(dto.getAuthor());
        dto.setDate(entity.getDate().getTime());
        dto.setDiscussionId(entity.getDiscussion().getId());
        dto.setId(dto.getId());
        dto.setText(dto.getText());
        return dto;
    }

    @Override
    public List<Message> fromDtoList(List<MessageDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<MessageDTO> toDtoList(List<Message> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
