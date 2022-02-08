package microservices.book.impl.services.persistent;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import microservices.book.api.domain.MultiplicationStatistic;

public abstract class DatabaseTestBase {

  protected static EntityManagerFactory emf;

  @AfterAll
  public static void shutDownEMF() {
    if (emf != null) {
      if (emf.isOpen()) {
        emf.close();
      }
      emf = null;
    }
  }

  @BeforeEach
  public void clearDatabase() {
    EntityManager em = null;
    EntityTransaction t = null;
    try {
      em = emf.createEntityManager();
      t = em.getTransaction();

      t.begin();
      Query qry = em.createNamedQuery("DELETE_ALL_USERS");
      qry.executeUpdate();
      t.commit();

      t.begin();
      List<UserE> users = em.createNamedQuery("SELECT_ALL_USERS", UserE.class).getResultList();
      List<MultiplicationAttemptE> attempts =
          em.createNamedQuery("SELECT_ALL_ATTEMPTS", MultiplicationAttemptE.class).getResultList();
      t.commit();

      assertTrue(users.isEmpty());
      assertTrue(attempts.isEmpty());
    } catch (Exception e) {
      if (t != null && t.isActive()) {
        t.rollback();
      }
      throw e;
    } finally {
      if (em != null && em.isOpen()) {
        em.close();
      }
    }
  }

  @Test
  public void saveTest() {
    EntityManager em = null;
    EntityTransaction t = null;
    try {
      em = emf.createEntityManager();
      t = em.getTransaction();

      t.begin();
      UserE user = new UserE("john");
      user.addAttempt(new MultiplicationAttemptE(2, 3, 6, true))
          .addAttempt(new MultiplicationAttemptE(3, 4, 12, true));
      em.persist(user);
      t.commit();

      t.begin();
      List<UserE> users = em.createNamedQuery("SELECT_ALL_USERS", UserE.class).getResultList();
      List<MultiplicationAttemptE> attempts =
          em.createNamedQuery("SELECT_ALL_ATTEMPTS", MultiplicationAttemptE.class).getResultList();
      t.commit();

      assertTrue(users.size() == 1);
      assertTrue(attempts.size() == 2);
    } catch (Exception e) {
      if (t != null && t.isActive()) {
        t.rollback();
      }
      throw e;
    } finally {
      if (em != null && em.isOpen()) {
        em.close();
      }
    }
  }

  @Test
  public void findByAliasTest() {
    EntityManager em = null;
    EntityTransaction t = null;
    try {
      em = emf.createEntityManager();
      t = em.getTransaction();

      t.begin();
      UserE user = new UserE("jone");
      user.addAttempt(new MultiplicationAttemptE(2, 3, 6, true))
          .addAttempt(new MultiplicationAttemptE(3, 4, 12, true));
      em.persist(user);
      t.commit();

      t.begin();
      List<UserE> users =
          em.createNamedQuery("FIND_BY_ALIAS", UserE.class)
              .setParameter("ualias", "jone")
              .getResultList();
      t.commit();

      assertTrue(users.size() == 1);
    } catch (Exception e) {
      if (t != null && t.isActive()) {
        t.rollback();
      }
      throw e;
    } finally {
      if (em != null && em.isOpen()) {
        em.close();
      }
    }
  }

  @Test
  public void getStaticsTest() {
    EntityManager em = null;
    EntityTransaction t = null;
    try {
      em = emf.createEntityManager();
      t = em.getTransaction();

      t.begin();
      UserE user = new UserE("jone");
      user.addAttempt(new MultiplicationAttemptE(2, 3, 6, true))
          .addAttempt(new MultiplicationAttemptE(3, 4, 12, true));
      em.persist(user);
      t.commit();

      t.begin();
      List<MultiplicationStatistic> stats =
          em.createNamedQuery("GET_STATISTICS", MultiplicationStatistic.class)
              .setParameter("ualias", user.getAlias())
              .getResultList();
      t.commit();

      assertNotNull(stats);
      assertTrue(stats.size() == 2);

      stats.forEach(System.out::println);
    } catch (Exception e) {
      if (t != null && t.isActive()) {
        t.rollback();
      }
      throw e;
    } finally {
      if (em != null && em.isOpen()) {
        em.close();
      }
    }
  }
}
