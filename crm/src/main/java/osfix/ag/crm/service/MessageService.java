package osfix.ag.crm.service;

import osfix.ag.crm.domain.feedback.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAll();
    List<Message> findAllByDiscussion(Long id);
    Message findById(Long id);
    Message update(Long id, Message message);
    Message save(Message message);
    void delete(Long id);
}
