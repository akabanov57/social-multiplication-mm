package microservices.book.impl.services.persistent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class EMFConfig {

    private EntityManagerFactory emf;

    public EMFConfig() {
    }

    @PostConstruct
    public void postConstruct() {
        emf = Persistence.createEntityManagerFactory("multiplication");
    }

    @PreDestroy
    public void preDestroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            emf = null;
        }
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return emf;
    }
}
