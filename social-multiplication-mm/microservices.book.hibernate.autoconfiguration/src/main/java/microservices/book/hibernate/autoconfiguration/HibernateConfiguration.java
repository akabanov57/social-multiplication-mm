package microservices.book.hibernate.autoconfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceProviderResolverHolder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HibernateConfiguration {

  private EntityManagerFactory emf;

  private static PersistenceProvider findProvider() {
    List<PersistenceProvider> providers =
        PersistenceProviderResolverHolder.getPersistenceProviderResolver()
            .getPersistenceProviders();

    PersistenceProvider provider = null;
    for (PersistenceProvider p : providers) {
      if (p instanceof org.hibernate.jpa.HibernatePersistenceProvider) {
        provider = p;
        break;
      }
    }

    return provider;
  }

  @PostConstruct
  void postConstruct() {

    PersistenceProvider provider = findProvider();

    Map<String, String> jpaProps = new HashMap<>();

    jpaProps.put("javax.persistence.jdbc.url", "jdbc:postgresql://pgsrv:5432/multiplication");
    jpaProps.put("javax.persistence.jdbc.user", "postgres");
    jpaProps.put("javax.persistence.jdbc.password", "postgres");
    jpaProps.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");

    jpaProps.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
    jpaProps.put("hibernate.show_sql", "true");
    jpaProps.put("hibernate.jdbc.batch_size", "20");

    emf = provider.createEntityManagerFactory("multiplication", jpaProps);
  }

  @PreDestroy
  void preDestroy() {
    if (emf != null) {
      if (emf.isOpen()) {
        emf.close();
      }
    }
  }

  @Bean
  EntityManagerFactory hibernateEntityManagerFactory() {
    return emf;
  }
}
