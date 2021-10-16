package microservices.book.impl.services.persistent;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microservices.book.api.domain.MultiplicationAttempt;
import microservices.book.api.domain.MultiplicationStatistic;
import microservices.book.api.services.persistent.Database;
import microservices.book.api.services.persistent.DatabaseException;

@Service
final class DatabaseImpl implements Database {

    private EntityManagerFactory emf;

    @Autowired
    DatabaseImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void saveAttempt(MultiplicationAttempt attempt, boolean correct) {
        Objects.requireNonNull(attempt, "attempt is null.");
        EntityManager em = null;
        EntityTransaction t = null;
        try {
            em = emf.createEntityManager();
            t = em.getTransaction();
            t.begin();
            List<UserE> users =
                    em.createNamedQuery("FIND_BY_ALIAS", UserE.class)
                            .setParameter("ualias", attempt.getUser().getAlias())
                            .getResultList();
            if (!users.isEmpty()) {
                users.get(0).addAttempt(MultiplicationAttemptE.from(attempt, correct));
            } else {
                UserE user = UserE.from(attempt.getUser());
                user.addAttempt(MultiplicationAttemptE.from(attempt, correct));
                em.persist(user);
            }
            t.commit();
        } catch (Exception e) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
            throw new DatabaseException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<MultiplicationStatistic> getStats(final String alias) {
        Objects.requireNonNull(alias, "alias is null.");
        EntityManager em = null;
        EntityTransaction t = null;
        final List<MultiplicationStatistic> stats;
        try {
            em = emf.createEntityManager();
            t = em.getTransaction();

            t.begin();
            stats =
                    em.createNamedQuery("GET_STATISTICS", MultiplicationStatistic.class)
                            .setParameter("ualias", alias)
                            .getResultList();
            t.commit();

        } catch (Exception e) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
            throw new DatabaseException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return stats;
    }
}
