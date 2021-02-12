package osfix.ag.crm.controller.manager;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.manager.PLC;
import osfix.ag.crm.service.PLCService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plc")
public class PLCController {
    PLCService plcService;

    public PLCController(PLCService plcService) {
        this.plcService = plcService;
    }

    @GetMapping("/")
    public List<PLC> getAll() { return plcService.findAll();}

    @GetMapping("/{id}")
    public PLC getById(@PathVariable(name = "id") long id) {return plcService.findById(id);}

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping()
    public PLC create(@RequestBody PLC plc) {
        return plcService.save(plc);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long id) { plcService.delete(id);}

    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @PutMapping("/{id}")
    public PLC update(@PathVariable(name = "id") Long id, @RequestBody PLC plc) {
        return plcService.update(id, plc);
    }
}
