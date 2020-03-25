package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.feedback.Discussion;
import osfix.ag.crm.domain.feedback.Message;
import osfix.ag.crm.service.DiscussionService;
import osfix.ag.crm.service.MessageService;
import osfix.ag.crm.service.dto.feedback.DiscussionDTO;
import osfix.ag.crm.service.dto.feedback.MessageDTO;
import osfix.ag.crm.service.mapper.feedback.DiscussionMapper;
import osfix.ag.crm.service.mapper.feedback.MessageMapper;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/feedback")
public class FeedbackController {

    private MessageService messageService;
    private DiscussionService discussionService;
    private MessageMapper messageMapper;
    private DiscussionMapper discussionMapper;

    public FeedbackController(MessageService messageService, DiscussionService discussionService, MessageMapper messageMapper, DiscussionMapper discussionMapper) {
        this.messageService = messageService;
        this.discussionService = discussionService;
        this.messageMapper = messageMapper;
        this.discussionMapper = discussionMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<Discussion>> findAll(){ return ResponseEntity.ok().body(discussionService.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Discussion> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(discussionService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Discussion> add(@RequestBody DiscussionDTO discussion) {
        return ResponseEntity.ok().body(discussionService.save(discussionMapper.toEntity(discussion)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discussion> update(@PathVariable(name = "id") Long id, @RequestBody DiscussionDTO discussion) {
        return ResponseEntity.ok().body(discussionService.update(id, discussionMapper.toEntity(discussion)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        discussionService.delete(id);
    }



    @GetMapping("/message/")
    public ResponseEntity<List<Message>> findMessageAll(){ return ResponseEntity.ok().body(messageService.findAll()); }

    @GetMapping("/message/{id}")
    public ResponseEntity<Message> findMessageById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(messageService.findById(id));
    }

    @PostMapping("/message/")
    public ResponseEntity<Message> messageAdd(@RequestBody MessageDTO message) {
        return ResponseEntity.ok().body(messageService.save(messageMapper.toEntity(message)));
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Message> messageUpdate(@PathVariable(name = "id") Long id, @RequestBody MessageDTO message) {
        return ResponseEntity.ok().body(messageService.update(id, messageMapper.toEntity(message)));
    }

    @DeleteMapping("/message/{id}")
    public void messageDelete(@PathVariable(name = "id") Long id) {
        messageService.delete(id);
    }
}
