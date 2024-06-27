package de.itstimetoforget.backend.rest.repository;

import de.itstimetoforget.backend.rest.entity.Employee;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@JaversSpringDataAuditable
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select e from Employee e where e.shortName = ?1")
    Optional<Employee> getEmployeeByShortName(@NonNull String shortName);

    @Query("select e.id from Employee e where e.shortName = ?1")
    Long getIdByShortName(@NonNull String shortName);
}
