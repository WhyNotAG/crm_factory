package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.service.WorkControlService;
import osfix.ag.crm.service.dto.WorkControlDTO;
import osfix.ag.crm.service.dto.WorkReportDTO;
import osfix.ag.crm.service.mapper.WorkControlMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/work_control")
public class WorkControlController {
    private WorkControlService workControlService;
    private WorkControlMapper workControlMapper;

    public WorkControlController(WorkControlService workControlService, WorkControlMapper workControlMapper) {
        this.workControlService = workControlService;
        this.workControlMapper = workControlMapper;
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

    @GetMapping("/day/{day}&{month}")
    public ResponseEntity<List<WorkControl>> findByDayAndMonth(@PathVariable(name = "day") Integer day,
                                                               @PathVariable(name = "month") Integer month) {
        return ResponseEntity.ok().body(workControlService.findByDayAndMonth(day,month));
    }

    @GetMapping("/report/{id}&{month}")
    public ResponseEntity<WorkReportDTO> reportByMonth(@PathVariable(name = "id") Long id, @PathVariable(name = "month") Integer month) {
        return ResponseEntity.ok().body(workControlService.reportByEmployeeId(id,month));
    }

    @PostMapping("/")
    public ResponseEntity<WorkControl> add(@RequestBody WorkControlDTO workControl) {
        return ResponseEntity.ok().body(workControlService.save(workControlMapper.toEntity(workControl)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkControl> update(@PathVariable(name = "id") Long id, @RequestBody WorkControlDTO workControl) {
        return ResponseEntity.ok().body(workControlService.update(id,workControlMapper.toEntity(workControl)));
    }

    @GetMapping("/product/{id}&{pr_id}&{quantity}")
    public ResponseEntity<WorkControl> addProduct(@PathVariable(name = "id") Long id,
                                                  @PathVariable(name = "pr_id") Long pr_id,
                                                  @PathVariable(name = "quantity") Long quantity) {
        return ResponseEntity.ok().body(workControlService.addProduct(id,pr_id,quantity));
    }

    @DeleteMapping("/product/{id}&{pr_id}")
    public ResponseEntity<WorkControl> deleteProduct(@PathVariable(name = "id") Long id,
                                                  @PathVariable(name = "pr_id") Long pr_id) {
        return ResponseEntity.ok().body(workControlService.deleteProduct(id,pr_id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        workControlService.delete(id);
    }
}

