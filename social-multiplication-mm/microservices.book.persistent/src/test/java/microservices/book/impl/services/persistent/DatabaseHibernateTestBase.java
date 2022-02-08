package microservices.book.impl.services.persistent;

import java.util.List;

import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceProviderResolverHolder;

public abstract class DatabaseHibernateTestBase extends DatabaseTestBase {

  static PersistenceProvider findProvider() {
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

    if (provider == null) {
      throw new RuntimeException("org.hibernate.jpa.HibernatePersistenceProvider not found.");
    }

    return provider;
  }
}
