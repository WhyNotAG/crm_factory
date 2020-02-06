package osfix.ag.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.WorkControl;
import osfix.ag.crm.service.WorkControlService;
import osfix.ag.crm.service.dto.ReWorkControlDTO;
import osfix.ag.crm.service.dto.WorkControlDTO;
import osfix.ag.crm.service.dto.WorkReportDTO;
import osfix.ag.crm.service.mapper.ReWorkControlMapper;
import osfix.ag.crm.service.mapper.WorkControlMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/work_control")
public class WorkControlController {
    private WorkControlService workControlService;
    private WorkControlMapper workControlMapper;
    private ReWorkControlMapper reWorkControlMapper;

    public WorkControlController(WorkControlService workControlService, WorkControlMapper workControlMapper,
                                 ReWorkControlMapper reWorkControlMapper) {
        this.workControlService = workControlService;
        this.workControlMapper = workControlMapper;
        this.reWorkControlMapper = reWorkControlMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReWorkControlDTO>> findAll() {
        return ResponseEntity.ok().body(reWorkControlMapper.toDtoList(workControlService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReWorkControlDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(reWorkControlMapper.fromEntity(workControlService.findById(id)));
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<ReWorkControlDTO>> findByEmployeeId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(reWorkControlMapper.toDtoList(workControlService.findAllByEmployeeId(id)));
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<ReWorkControlDTO>> findByMonth(@PathVariable(name = "month") Integer month) {
        return ResponseEntity.ok().body(reWorkControlMapper.toDtoList(workControlService.findAllByMonth(month)));
    }

    @GetMapping("/day/{day}&{month}")
    public ResponseEntity<List<ReWorkControlDTO>> findByDayAndMonth(@PathVariable(name = "day") Integer day,
                                                               @PathVariable(name = "month") Integer month) {
        return ResponseEntity.ok().body(reWorkControlMapper.toDtoList(workControlService.findByDayAndMonth(day,month)));
    }

    @GetMapping("/report/{id}&{month}")
    public ResponseEntity<WorkReportDTO> reportByMonth(@PathVariable(name = "id") Long id, @PathVariable(name = "month") Integer month) {
        return ResponseEntity.ok().body(workControlService.reportByEmployeeId(id,month));
    }

    @PostMapping("/")
    public ResponseEntity<ReWorkControlDTO> add(@RequestBody WorkControlDTO workControl) {
        return ResponseEntity.ok().body(reWorkControlMapper.fromEntity(workControlService.save(workControlMapper.toEntity(workControl))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReWorkControlDTO> update(@PathVariable(name = "id") Long id, @RequestBody WorkControlDTO workControl) {
        return ResponseEntity.ok().body(reWorkControlMapper.fromEntity(workControlService.update(id,
                workControlMapper.toEntity(workControl))));
    }

    @GetMapping("/product/{id}&{pr_id}&{quantity}")
    public ResponseEntity<ReWorkControlDTO> addProduct(@PathVariable(name = "id") Long id,
                                                  @PathVariable(name = "pr_id") Long pr_id,
                                                  @PathVariable(name = "quantity") Long quantity) {
        return ResponseEntity.ok().body(reWorkControlMapper.fromEntity(workControlService.addProduct(id,pr_id,quantity)));
    }

    @DeleteMapping("/product/{id}&{pr_id}")
    public ResponseEntity<ReWorkControlDTO> deleteProduct(@PathVariable(name = "id") Long id,
                                                  @PathVariable(name = "pr_id") Long pr_id) {
        return ResponseEntity.ok().body(reWorkControlMapper.fromEntity(workControlService.deleteProduct(id,pr_id)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        workControlService.delete(id);
    }

    @GetMapping("/range/{dF}&{mF}&{dL}&{mL}")
    public ResponseEntity<List<ReWorkControlDTO>> findByRange(@PathVariable(name = "dF") Integer dF,
                                                              @PathVariable(name = "mF") Integer mF,
                                                              @PathVariable(name = "dL") Integer dL,
                                                              @PathVariable(name = "mL") Integer mL) {
        return ResponseEntity.ok().body(reWorkControlMapper.toDtoList(workControlService.findByRange(dF,mF,dL,mL)));
    }
}

