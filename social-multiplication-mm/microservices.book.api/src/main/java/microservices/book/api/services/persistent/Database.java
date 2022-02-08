package microservices.book.api.services.persistent;

import java.util.List;

import microservices.book.api.domain.MultiplicationAttempt;
import microservices.book.api.domain.MultiplicationStatistic;

public interface Database {

  /**
   * Save multiplication attempt.
   *
   * @param attempt
   * @param correct
   */
  void saveAttempt(MultiplicationAttempt attempt, boolean correct);

  /**
   * Get multiplication statistics for user.
   *
   * @param alias the user's alias for whom we are getting statistics.
   * @return list of MultiplicationStatistic.
   */
  List<MultiplicationStatistic> getStats(final String alias);

  /** Clears data base. */
  void clear();
}
