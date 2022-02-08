package microservices.book.database.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import microservices.book.api.domain.Multiplication;
import microservices.book.api.domain.MultiplicationAttempt;
import microservices.book.api.domain.MultiplicationStatistic;
import microservices.book.api.domain.User;
import microservices.book.api.services.persistent.Database;

@SpringBootTest(classes = {DatabaseImplTestConf.class})
public class DatabaseImplTest {

  @Autowired
  private Database db;

  @BeforeEach
  public void clearDatabase() {
    assertNotNull(db);

    db.clear();
  }

  @Test
  public void dbTest() {
    assertNotNull(db);

    db.saveAttempt(new MultiplicationAttempt(new User("jon"), new Multiplication(2, 3), 6), true);
    db.saveAttempt(new MultiplicationAttempt(new User("jon"), new Multiplication(3, 4), 12), true);
    db.saveAttempt(new MultiplicationAttempt(new User("tom"), new Multiplication(4, 5), 20), true);

    List<MultiplicationStatistic> stat = db.getStats("jon");
    assertEquals(2, stat.size());

    stat = db.getStats("tom");
    assertEquals(1, stat.size());
  }

}
