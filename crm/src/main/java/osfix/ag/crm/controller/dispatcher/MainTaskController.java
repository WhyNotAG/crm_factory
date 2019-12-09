package osfix.ag.crm.controller.dispatcher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.dispatcher.MainTask;
import osfix.ag.crm.service.MainTaskService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mainTask")
public class MainTaskController {
    private MainTaskService mainTaskService;

    public MainTaskController(MainTaskService mainTaskService) {
        this.mainTaskService = mainTaskService;
    }

    @GetMapping("/")
    public ResponseEntity<List<MainTask>> findAll() { return ResponseEntity.ok().body(mainTaskService.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<MainTask> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(mainTaskService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<MainTask> add(@RequestBody MainTask mainTask) {
        return ResponseEntity.ok().body(mainTaskService.save(mainTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MainTask> update(@PathVariable(name = "id") Long id, @RequestBody MainTask mainTask) {
        return ResponseEntity.ok().body(mainTaskService.update(id, mainTask));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) { mainTaskService.delete(id); }
}
