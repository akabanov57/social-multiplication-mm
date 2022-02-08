package microservices.book.impl.services.persistent;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.spi.PersistenceProvider;

import org.junit.jupiter.api.BeforeAll;

public class DatabaseHibernatePGJdbcNGTest extends DatabaseHibernateTestBase {

  @BeforeAll
  public static void initEMF() {
    PersistenceProvider provider = findProvider();

    Map<String, String> jpaProps = new HashMap<>();

    jpaProps.put("javax.persistence.jdbc.url", "jdbc:pgsql://pgsrv:5432/multiplication");
    jpaProps.put("javax.persistence.jdbc.user", "postgres");
    jpaProps.put("javax.persistence.jdbc.password", "postgres");
    jpaProps.put("javax.persistence.jdbc.driver", "com.impossibl.postgres.jdbc.PGDriver");

    jpaProps.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
    jpaProps.put("hibernate.show_sql", "true");
    jpaProps.put("hibernate.jdbc.batch_size", "20");

    emf = provider.createEntityManagerFactory("multiplication", jpaProps);
  }
}
