package microservices.book.impl.services.persistent;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.spi.PersistenceProvider;

import org.junit.jupiter.api.BeforeAll;

public class DatabaseEclipseLinkPostgreSQLJdbcTest extends DatabaseEclipseLinkTestBase {

  @BeforeAll
  public static void initEMF() {
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
}
