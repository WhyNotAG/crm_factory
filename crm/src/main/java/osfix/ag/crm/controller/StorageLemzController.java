package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.StorageLemz;
import osfix.ag.crm.service.StorageLemzService;

import java.util.List;

@RestController
@RequestMapping("api/v1/lemz_storage")
public class StorageLemzController {
    private StorageLemzService storageLemzService;

    public StorageLemzController(StorageLemzService storageLemzService) { this.storageLemzService = storageLemzService; }

    @GetMapping("/")
    public ResponseEntity<List<StorageLemz>> findAll() { return ResponseEntity.ok().body(storageLemzService.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<StorageLemz> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(storageLemzService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<StorageLemz> add(@RequestBody StorageLemz storageLemz) {
        return ResponseEntity.ok().body(storageLemzService.save(storageLemz));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StorageLemz> update(@PathVariable(name = "id") Long id, @RequestBody StorageLemz storageLemz) {
        return ResponseEntity.ok().body(storageLemzService.update(id, storageLemz));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        storageLemzService.delete(id);
    }


}
