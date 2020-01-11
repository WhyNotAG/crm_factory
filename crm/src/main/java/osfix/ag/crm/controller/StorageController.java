package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.Storage;
import osfix.ag.crm.service.StorageService;

import java.util.List;

@RestController
@RequestMapping("api/v1/storage")
public class StorageController {
    private StorageService storageService;

    public StorageController(StorageService storageService) { this.storageService = storageService; }

    @GetMapping("/")
    public ResponseEntity<List<Storage>> findAll() { return ResponseEntity.ok().body(storageService.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Storage> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(storageService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Storage> add(@RequestBody Storage storage) {
        return ResponseEntity.ok().body(storageService.save(storage));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Storage> update(@PathVariable(name = "id") Long id, @RequestBody Storage storage) {
        return ResponseEntity.ok().body(storageService.update(id, storage));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        storageService.delete(id);
    }


}
