package de.itstimetoforget.backend.rest.security;

import org.javers.spring.auditable.AuthorProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityAuthorProvider implements AuthorProvider {
    @Override
    public String provide() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "";
    }
}
