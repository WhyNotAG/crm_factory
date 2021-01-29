package osfix.ag.crm.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import osfix.ag.crm.domain.Log;
import osfix.ag.crm.service.LogService;

@RestController
@RequestMapping("/api/v1/log")
public class LogController {

    LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/")
    public Page<Log> getAllClients(@PageableDefault(sort = {"date"}, direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
       return logService.findAll(pageable);
    }
}
