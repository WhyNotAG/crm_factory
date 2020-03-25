package osfix.ag.crm.service;

import osfix.ag.crm.domain.feedback.Discussion;

import java.util.List;

public interface DiscussionService {
    List<Discussion> findAll();
    Discussion findById(Long id);
    Discussion update(Long id, Discussion discussion);
    Discussion save(Discussion discussion);
    void delete(Long id);
}
