package osfix.ag.crm.service;

import osfix.ag.crm.domain.admin.MessageTemplate;

import java.util.List;

public interface MessageTemplateService {
    List<MessageTemplate> findAll();
    MessageTemplate findById(Long id);
    MessageTemplate update(Long id, MessageTemplate message);
    MessageTemplate save(MessageTemplate message);
    void delete(Long id);
}
