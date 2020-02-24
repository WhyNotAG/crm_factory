package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.TrashBase;
import osfix.ag.crm.service.TrashBaseService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/trashBase")
public class TrashBaseController {
    TrashBaseService trashBaseService;

    public TrashBaseController(TrashBaseService trashBaseService) {
        this.trashBaseService = trashBaseService;
    }

    @GetMapping("/")
    public ResponseEntity<List<TrashBase>> getAll() {
        return ResponseEntity.ok().body(trashBaseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrashBase> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(trashBaseService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<TrashBase> add(@RequestBody TrashBase trashBase) {
        return ResponseEntity.ok().body(trashBaseService.save(trashBase));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrashBase> update(@PathVariable(name = "id") Long id, @RequestBody TrashBase trashBase) {
        return ResponseEntity.ok().body(trashBaseService.update(id, trashBase));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        trashBaseService.delete(id);
    }
}
