package microservices.book.impl.services.persistent;

import java.util.List;

import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceProviderResolverHolder;

public abstract class DatabaseEclipseLinkTestBase extends DatabaseTestBase {

  static PersistenceProvider findProvider() {
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
}
