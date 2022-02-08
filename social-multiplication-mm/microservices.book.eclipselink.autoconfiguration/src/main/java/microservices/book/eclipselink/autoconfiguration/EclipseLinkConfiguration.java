package microservices.book.eclipselink.autoconfiguration;

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
class EclipseLinkConfiguration {

  private EntityManagerFactory emf;

  @PostConstruct
  void postConstruct() {
    PersistenceProvider provider = findProvider();

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

  private static PersistenceProvider findProvider() {
    List<PersistenceProvider> providers =
        PersistenceProviderResolverHolder.getPersistenceProviderResolver()
            .getPersistenceProviders();

    PersistenceProvider provider = null;
    for (PersistenceProvider p : providers) {
      if (p instanceof org.eclipse.persistence.jpa.PersistenceProvider) {
        provider = p;
        break;
      }
    }

    return provider;
  }

  @Bean
  EntityManagerFactory eclipseEntityManagerFactory() {
    return emf;
  }
}
