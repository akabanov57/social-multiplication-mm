package microservices.book.impl.services.persistent;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The configuration is needed for integration tests. During the development of the database is not
 * needed.
 *
 * @author andrei
 */
// @Configuration
class EMFConfig {

  private EntityManagerFactory emf;

  public EMFConfig() {}

  // @PostConstruct
  public void postConstruct() {
    emf = Persistence.createEntityManagerFactory("multiplication");
  }

  // @PreDestroy
  public void preDestroy() {
    if (emf != null && emf.isOpen()) {
      emf.close();
      emf = null;
    }
  }

  // @Bean
  public EntityManagerFactory entityManagerFactory() {
    return emf;
  }
}
