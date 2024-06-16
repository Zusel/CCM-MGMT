package de.itstimetoforget.backend.rest.repository;

import de.itstimetoforget.backend.rest.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, Long> {
}
