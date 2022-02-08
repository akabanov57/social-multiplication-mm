package microservices.book.database.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;

import microservices.book.api.services.persistent.Database;

// @Configuration
class DatabaseImplTestConf {

  private EntityManagerFactory emf;

  @PostConstruct
  void postConstruct() {
    Map<String, String> jpaProps = new HashMap<>();

    jpaProps.put("javax.persistence.jdbc.url", "jdbc:postgresql://pgsrv:5432/multiplication");
    jpaProps.put("javax.persistence.jdbc.user", "postgres");
    jpaProps.put("javax.persistence.jdbc.password", "postgres");
    jpaProps.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");

    jpaProps.put("eclipselink.logging.level", "INFO");
    jpaProps.put("eclipselink.logging.level.sql", "FINE");
    jpaProps.put(
        "eclipselink.target-database",
        "org.eclipse.persistence.platform.database.PostgreSQLPlatform");
    jpaProps.put("eclipselink.jdbc.batch-writing", "jdbc");
    jpaProps.put("eclipselink.jdbc.batch-writing.size", "150");

    emf = Persistence.createEntityManagerFactory("multiplication", jpaProps);
  }

  @PreDestroy
  void preDestroy() {
    if (emf != null) {
      if (emf.isOpen()) {
        emf.close();
      }
      emf = null;
    }
  }

  @Bean
  EntityManagerFactory entityManagerFactory() {
    return emf;
  }

  @Bean
  Database database() {
    return new DatabaseImpl(entityManagerFactory());
  }
}
