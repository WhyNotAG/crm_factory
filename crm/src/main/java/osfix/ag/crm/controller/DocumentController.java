package osfix.ag.crm.controller;

import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.Document;
import osfix.ag.crm.service.DocumentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/document")
public class DocumentController {
    private DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/")
    public List<Document> getAllDocuments() {
        return documentService.findAll();
    }

    @GetMapping("/{id}")
    public Document getDocument(@PathVariable(name = "id") long id) {
        return documentService.findById(id);
    }

    @PostMapping("/")
    public Document create(@RequestBody Document document) {
        return documentService.save(document);
    }

    @PutMapping("/{id}")
    public Document update(@PathVariable(name = "id") long id, @RequestBody Document document) {
        return documentService.update(id, document);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long id) { documentService.delete(id); }
}
