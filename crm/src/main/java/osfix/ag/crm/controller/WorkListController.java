package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.WorkList;
import osfix.ag.crm.service.WorkListService;
import osfix.ag.crm.service.dto.WorkListDTO;
import osfix.ag.crm.service.mapper.WorkListMapper;

import java.util.List;

@RestController
@RequestMapping("api/v1/work-list")
public class WorkListController {
    private WorkListService workListService;
    private WorkListMapper workListMapper;

    public WorkListController(WorkListService workListService, WorkListMapper workListMapper) {
        this.workListService = workListService;
        this.workListMapper = workListMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<WorkList>> findAll() { return ResponseEntity.ok().body(workListService.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<WorkList> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(workListService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<WorkList> add(@RequestBody WorkListDTO workList) {
        return ResponseEntity.ok().body(workListService.save(workListMapper.toEntity(workList)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkList> update(@PathVariable(name = "id") Long id, @RequestBody WorkListDTO workList) {
        return ResponseEntity.ok().body(workListService.update(id, workListMapper.toEntity(workList)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        workListService.delete(id);
    }
}
