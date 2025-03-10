package ${package};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EntityScan(basePackages = {"${package}.entity"})
@EnableJpaRepositories(basePackages = "${package}.repo")
public class LogicApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogicApplication.class, args);
    }
}