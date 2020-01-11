package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.WorkList;
import osfix.ag.crm.service.WorkListService;

import java.util.List;

@RestController
@RequestMapping("api/v1/work-list")
public class WorkListController {
    private WorkListService workListService;

    public WorkListController(WorkListService workListService) {
        this.workListService = workListService;
    }

    @GetMapping("/")
    public ResponseEntity<List<WorkList>> findAll() { return ResponseEntity.ok().body(workListService.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<WorkList> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(workListService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<WorkList> add(@RequestBody WorkList workList) {
        return ResponseEntity.ok().body(workListService.save(workList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkList> update(@PathVariable(name = "id") Long id, @RequestBody WorkList workList) {
        return ResponseEntity.ok().body(workListService.update(id, workList));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        workListService.delete(id);
    }
}
