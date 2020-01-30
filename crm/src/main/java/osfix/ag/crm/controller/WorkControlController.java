package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.service.WorkControlService;
import osfix.ag.crm.service.dto.WorkReportDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/work_control")
public class WorkControlController {
    private WorkControlService workControlService;

    public WorkControlController(WorkControlService workControlService) {
        this.workControlService = workControlService;
    }

    @GetMapping("/")
    public ResponseEntity<List<WorkControl>> findAll() {
        return ResponseEntity.ok().body(workControlService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkControl> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(workControlService.findById(id));
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<WorkControl>> findByEmployeeId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(workControlService.findAllByEmployeeId(id));
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<WorkControl>> findByMonth(@PathVariable(name = "month") Integer month) {
        return ResponseEntity.ok().body(workControlService.findAllByMonth(month));
    }

    @GetMapping("/report/{id}&{month}")
    public ResponseEntity<WorkReportDTO> reportByMonth(@PathVariable(name = "id") Long id, @PathVariable(name = "month") Integer month) {
        return ResponseEntity.ok().body(workControlService.reportByEmployeeId(id,month));
    }

    @PostMapping("/")
    public ResponseEntity<WorkControl> add(@RequestBody WorkControl workControl) {
        return ResponseEntity.ok().body(workControlService.save(workControl));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkControl> update(@PathVariable(name = "id") Long id, @RequestBody WorkControl workControl) {
        return ResponseEntity.ok().body(workControlService.update(id,workControl));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        workControlService.delete(id);
    }
}

