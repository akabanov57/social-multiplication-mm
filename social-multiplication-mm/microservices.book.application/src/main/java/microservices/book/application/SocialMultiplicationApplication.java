package microservices.book.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {
      "microservices.book.impl.services.multiplication",
      "microservices.book.controllers"
    })
public class SocialMultiplicationApplication {

  public static void main(String[] args) {
    SpringApplication.run(SocialMultiplicationApplication.class, args);
  }
}
