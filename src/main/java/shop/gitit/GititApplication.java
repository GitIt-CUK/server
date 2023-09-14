package shop.gitit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class GititApplication {

    public static void main(String[] args) {
        SpringApplication.run(GititApplication.class, args);
    }
}
