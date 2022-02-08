package microservices.book.hibernate.springlocal.autoconfiguration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@Configuration
class HibernateConfiguration {

  @Bean
  LocalEntityManagerFactoryBean hibernateLocalEntityManagerFactoryBean() {

    LocalEntityManagerFactoryBean emff = new LocalEntityManagerFactoryBean();

    emff.setPersistenceProviderClass(org.hibernate.jpa.HibernatePersistenceProvider.class);
    emff.setPersistenceUnitName("multiplication");

    Map<String, String> jpaProps = new HashMap<>();

    jpaProps.put("javax.persistence.jdbc.url", "jdbc:postgresql://pgsrv:5432/multiplication");
    jpaProps.put("javax.persistence.jdbc.user", "postgres");
    jpaProps.put("javax.persistence.jdbc.password", "postgres");
    jpaProps.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");

    jpaProps.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
    jpaProps.put("hibernate.show_sql", "true");
    jpaProps.put("hibernate.jdbc.batch_size", "20");

    emff.setJpaPropertyMap(jpaProps);
    return emff;
  }
}
