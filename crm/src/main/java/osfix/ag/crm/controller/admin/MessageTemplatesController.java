package osfix.ag.crm.controller.admin;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.admin.Journal;
import osfix.ag.crm.domain.admin.MessageTemplate;
import osfix.ag.crm.service.MessageService;
import osfix.ag.crm.service.MessageTemplateService;
import osfix.ag.crm.service.dto.admin.JournalDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message_templates")
public class MessageTemplatesController {
    private MessageTemplateService messageTemplateService;

    public MessageTemplatesController(MessageTemplateService messageTemplateService) {
        this.messageTemplateService = messageTemplateService;
    }

    @GetMapping("/")
    public ResponseEntity<List<MessageTemplate>> getAllClients() {
        return ResponseEntity.ok().body(messageTemplateService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity <MessageTemplate> add(@RequestBody MessageTemplate messageTemplate) {
        return ResponseEntity.ok().body(messageTemplateService.save(messageTemplate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageTemplate> getById (@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(messageTemplateService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageTemplate> update(@PathVariable(name = "id") Long id, @RequestBody MessageTemplate messageTemplate) {
        return ResponseEntity.ok().body(
                messageTemplateService.update(id, messageTemplate));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        messageTemplateService.delete(id);
    }

}
