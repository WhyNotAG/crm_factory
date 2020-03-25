package osfix.ag.crm.service.impl.feedback;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.feedback.Message;
import osfix.ag.crm.repo.feedback.MessageRepo;
import osfix.ag.crm.service.MessageService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    MessageRepo messageRepo;

    public MessageServiceImpl(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public List<Message> findAll() {
        return messageRepo.findAll();
    }

    @Override
    public Message findById(Long id) {
        return messageRepo.findById(id).orElse(null);
    }

    @Override
    public Message update(Long id, Message message) {
        Message messageFromDb = messageRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(message, messageFromDb, "id");
        return messageRepo.save(messageFromDb);
    }

    @Override
    public Message save(Message message) {
        return messageRepo.save(message);
    }

    @Override
    public void delete(Long id) {
        messageRepo.deleteById(id);
    }

    @Override
    public List<Message> findAllByDiscussion(Long id) {
        return messageRepo.findAllByDiscussion_Id(id);
    }
}
