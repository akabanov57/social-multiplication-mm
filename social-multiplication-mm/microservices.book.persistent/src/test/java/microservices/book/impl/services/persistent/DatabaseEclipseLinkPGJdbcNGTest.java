package microservices.book.impl.services.persistent;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.spi.PersistenceProvider;

import org.junit.jupiter.api.BeforeAll;

/**
 * The @SpringBootTest is needed for integration tests. During the development of the database is
 * not needed.
 *
 * @author andrei
 */
// @SpringBootTest(classes = {EMFConfig.class})
public class DatabaseEclipseLinkPGJdbcNGTest extends DatabaseEclipseLinkTestBase {

  // @Autowired
  //  private static EntityManagerFactory emf;

  @BeforeAll
  public static void initEMF() {
    PersistenceProvider provider = findProvider();

    Map<String, String> jpaProps = new HashMap<>();

    jpaProps.put("javax.persistence.jdbc.url", "jdbc:pgsql://pgsrv:5432/multiplication");
    jpaProps.put("javax.persistence.jdbc.user", "postgres");
    jpaProps.put("javax.persistence.jdbc.password", "postgres");
    jpaProps.put("javax.persistence.jdbc.driver", "com.impossibl.postgres.jdbc.PGDriver");

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
