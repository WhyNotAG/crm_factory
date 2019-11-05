package osfix.ag.crm.service;

import osfix.ag.crm.domain.Document;

import java.util.List;

public interface DocumentService {
    List<Document> findAll();
    Document findById(Long id);
    Document update(Long id, Document document);
    Document save(Document document);
    void delete(Long id);
}
