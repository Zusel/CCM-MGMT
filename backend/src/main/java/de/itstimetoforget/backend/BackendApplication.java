package de.itstimetoforget.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"de.itstimetoforget.backend"})
@EnableJpaRepositories(basePackages = "de.itstimetoforget.backend", entityManagerFactoryRef = "entityManagerFactory")
@EnableTransactionManagement
@EntityScan(basePackages = "de.itstimetoforget.backend.rest.entity")

public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
