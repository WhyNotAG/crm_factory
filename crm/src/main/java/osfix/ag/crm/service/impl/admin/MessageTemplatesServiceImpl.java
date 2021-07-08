package osfix.ag.crm.service.impl.admin;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.admin.MessageTemplate;
import osfix.ag.crm.repo.admin.MessageTemplateRepo;
import osfix.ag.crm.service.MessageTemplateService;

import java.util.List;

@Service
public class MessageTemplatesServiceImpl implements MessageTemplateService {

    private MessageTemplateRepo messageTemplateRepo;

    public MessageTemplatesServiceImpl(MessageTemplateRepo messageTemplateRepo) {
        this.messageTemplateRepo = messageTemplateRepo;
    }

    @Override
    public List<MessageTemplate> findAll() {
        return messageTemplateRepo.findAll();
    }

    @Override
    public MessageTemplate findById(Long id) {
        return messageTemplateRepo.findById(id).orElse(null);
    }

    @Override
    public MessageTemplate update(Long id, MessageTemplate message) {
        MessageTemplate messageTemplateFromDb = messageTemplateRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(message, messageTemplateFromDb, "id");
        return messageTemplateRepo.save(messageTemplateFromDb);
    }

    @Override
    public MessageTemplate save(MessageTemplate message) {
        return messageTemplateRepo.save(message);
    }

    @Override
    public void delete(Long id) {
        messageTemplateRepo.deleteById(id);
    }
}
