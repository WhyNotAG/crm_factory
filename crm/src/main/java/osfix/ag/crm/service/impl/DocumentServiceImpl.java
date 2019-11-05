package osfix.ag.crm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Client;
import osfix.ag.crm.domain.Document;
import osfix.ag.crm.repo.DocumentRepo;
import osfix.ag.crm.service.DocumentService;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    private DocumentRepo documentRepo;

    public DocumentServiceImpl(DocumentRepo documentRepo) {
        this.documentRepo = documentRepo;
    }

    @Override
    public List<Document> findAll() { return documentRepo.findAll(); }

    @Override
    public Document findById(Long id) {
        return findId(id);
    }

    @Override
    public Document update(Long id, Document document) {
        Document documentFromDB = findId(id);
        BeanUtils.copyProperties(document, documentFromDB);
        documentRepo.save(documentFromDB);
        return documentFromDB;
    }

    @Override
    public Document save(Document document) { return documentRepo.save(document);}

    @Override
    public void delete(Long id) {
        documentRepo.deleteById(id);
    }

    public Document findId(Long id) {
        Document document = documentRepo.findById(id).orElse(null);
        if (document == null) {
            return null;
        } else {
            return document;
        }
    }
}
