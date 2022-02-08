package microservices.book.database.jpa;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import microservices.book.api.services.persistent.Database;

@Configuration
class DatabaseConfiguration {

  @Bean
  Database database(EntityManagerFactory emf) {
    return new DatabaseImpl(emf);
  }
}
