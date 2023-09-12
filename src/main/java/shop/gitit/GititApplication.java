package shop.gitit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GititApplication {

    public static void main(String[] args) {
        SpringApplication.run(GititApplication.class, args);
    }
}
