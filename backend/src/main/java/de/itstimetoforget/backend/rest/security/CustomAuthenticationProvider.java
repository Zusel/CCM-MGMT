package de.itstimetoforget.backend.rest.security;

import de.itstimetoforget.backend.rest.service.EmployeeService;
import org.hibernate.service.spi.InjectService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private EmployeeService employeeService;

    public CustomAuthenticationProvider(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Long employeeId = employeeService.getIdByShortName((String) authentication.getPrincipal());
        if (employeeId != null) {
            return new UsernamePasswordAuthenticationToken(employeeId, null, new ArrayList<>());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
