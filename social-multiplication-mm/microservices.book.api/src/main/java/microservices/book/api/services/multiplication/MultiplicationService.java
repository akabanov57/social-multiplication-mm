package microservices.book.api.services.multiplication;

import java.util.List;

import microservices.book.api.domain.Multiplication;
import microservices.book.api.domain.MultiplicationAttempt;
import microservices.book.api.domain.MultiplicationStatistic;

public interface MultiplicationService {

  /**
   * Creates a Multiplication object with two randomly-­generated factors between 11 and 99.
   *
   * @return a Multiplication object with random factors
   */
  Multiplication createRandomMultiplication();

  /** @return true if the attempt matches the result of the multiplication, false otherwise. */
  boolean checkAttempt(final MultiplicationAttempt resultAttempt);

  /**
   * Gets the statistics for a given user.
   *
   * @param alias user's alias
   * @return list of MultiplicationStatistic.
   */
  List<MultiplicationStatistic> getStatsForUser(final String alias);
}
