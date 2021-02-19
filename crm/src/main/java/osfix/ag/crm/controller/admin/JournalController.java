package osfix.ag.crm.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.admin.Journal;
import osfix.ag.crm.service.JournalService;
import osfix.ag.crm.service.dto.admin.JournalDTO;
import osfix.ag.crm.service.mapper.admin.JournalMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/journal")
public class JournalController {
    JournalService journalService;
    JournalMapper journalMapper;

    public JournalController(JournalService journalService, JournalMapper journalMapper) {
        this.journalService = journalService;
        this.journalMapper = journalMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<JournalDTO>> getAllClients() {
        return ResponseEntity.ok().body(journalMapper.toDtoList(journalService.findAll()));
    }

    @PostMapping("/")
    public ResponseEntity <JournalDTO> add(@RequestBody JournalDTO journal) {
        return ResponseEntity.ok().body(journalMapper.fromEntity(journalService.save(journalMapper.toEntity(journal))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalDTO> getById (@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(journalMapper.fromEntity(journalService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalDTO> update(@PathVariable(name = "id") Long id, @RequestBody JournalDTO journal) {
        return ResponseEntity.ok().body(
                journalMapper.fromEntity(journalService.update(id, journalMapper.toEntity(journal))));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        journalService.delete(id);
    }

    @PostMapping("/date/")
    public ResponseEntity<List<JournalDTO>> findByDate(@RequestBody JournalDTO journal) {
        return ResponseEntity.ok().body(
                journalMapper.toDtoList(journalService.findAllByDate(journal.getDate())));
    }
}
