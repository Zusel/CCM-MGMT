package de.itstimetoforget.backend.rest.resource;

import de.itstimetoforget.backend.rest.entity.Customer;
import de.itstimetoforget.backend.rest.entity.Employee;
import de.itstimetoforget.backend.rest.entity.History;
import de.itstimetoforget.backend.rest.entity.Order;
import de.itstimetoforget.backend.rest.service.HistoryService;
import jakarta.websocket.server.PathParam;
import javassist.NotFoundException;
import org.javers.core.Changes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 4800)
@RequestMapping(path = "/history")
public class HistoryResource {


    private HistoryService historyService;

    public HistoryResource(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping(path = "")
    public List<History> getCompleteHistory() {
        return historyService.getCompleteHistory();
    }

    @GetMapping(value = {"/{type}", "/{type}/{id}"})
    public List<History> getHistoryByType(@PathVariable String type, @PathVariable(required = false) Long id) throws NotFoundException {
        return historyService.getChangesByClass(type, id);
    }
}
