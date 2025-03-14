package ${package};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication()
@EnableFeignClients(basePackages = "${package}.client")
public class UIApplication {
    public static void main(String[] args) {
        SpringApplication.run(UIApplication.class, args);
    }
}