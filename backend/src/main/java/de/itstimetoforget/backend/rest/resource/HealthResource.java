package de.itstimetoforget.backend.rest.resource;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthResource {

    @GetMapping("")
    public void health(HttpServletResponse responseEntity) {
        responseEntity.setStatus(HttpServletResponse.SC_OK);
    }
}
