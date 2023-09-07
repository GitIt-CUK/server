package shop.gitit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class GititApplication {

    public static void main(String[] args) {
        SpringApplication.run(GititApplication.class, args);
    }
}
