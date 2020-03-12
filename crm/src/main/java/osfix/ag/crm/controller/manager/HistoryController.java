package osfix.ag.crm.controller.manager;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.manager.History;
import osfix.ag.crm.service.HistoryService;
import osfix.ag.crm.service.dto.manager.HistoryDTO;
import osfix.ag.crm.service.mapper.manager.HistoryMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/history")
public class HistoryController {
    public HistoryService historyService;
    private HistoryMapper historyMapper;

    public HistoryController(HistoryService historyService, HistoryMapper historyMapper) {
        this.historyMapper = historyMapper;
        this.historyService = historyService;
    }

    @GetMapping("/")
    public List<History> getAll() { return historyService.findAll();}

    @GetMapping("/{id}")
    public History getById(@PathVariable(name="id") long id) {return historyService.findById(id);}

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping()
    public History create(@RequestBody HistoryDTO history) {
        return historyService.save(historyMapper.toEntity(history));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name="id") long id) { historyService.delete(id);}

    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @PutMapping("/{id}")
    public History update(@PathVariable(name="id") Long id, @RequestBody HistoryDTO history) {
        return historyService.update(id, historyMapper.toEntity(history));
    }
}
