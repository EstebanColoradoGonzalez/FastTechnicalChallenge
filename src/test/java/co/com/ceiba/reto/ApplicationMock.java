package co.com.ceiba.reto;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "co.com.ceiba.reto")
@EnableJpaRepositories(basePackages = "co.com.ceiba.reto")
public class ApplicationMock {
}