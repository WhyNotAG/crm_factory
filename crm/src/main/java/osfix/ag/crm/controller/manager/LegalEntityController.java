package osfix.ag.crm.controller.manager;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import osfix.ag.crm.domain.manager.LegalEntity;
import osfix.ag.crm.service.LegalEntityService;
import osfix.ag.crm.service.dto.manager.LegalEntityDTO;
import osfix.ag.crm.service.mapper.manager.LegalEntityMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/legal")
public class LegalEntityController {
    public LegalEntityService legalEntityService;
    private LegalEntityMapper legalEntityMapper;

    public LegalEntityController(LegalEntityService legalEntityService, LegalEntityMapper legalEntityMapper) {
        this.legalEntityMapper = legalEntityMapper;
        this.legalEntityService = legalEntityService;
    }

    @GetMapping("/")
    public List<LegalEntity> getAll() { return legalEntityService.findAll();}

    @GetMapping("/{id}")
    public LegalEntity getById(@PathVariable(name="id") long id) {return legalEntityService.findById(id);}

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping()
    public LegalEntity create(@RequestBody LegalEntityDTO legalEntity) {
        return legalEntityService.save(legalEntityMapper.toEntity(legalEntity));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name="id") long id) { legalEntityService.delete(id);}

    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @PutMapping("/{id}")
    public LegalEntity update(@PathVariable(name="id") Long id, @RequestBody LegalEntityDTO legalEntity) {
        return legalEntityService.update(id, legalEntityMapper.toEntity(legalEntity));
    }
}
