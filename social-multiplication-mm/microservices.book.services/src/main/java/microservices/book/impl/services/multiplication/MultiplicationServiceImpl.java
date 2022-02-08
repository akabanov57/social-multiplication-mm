package microservices.book.impl.services.multiplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microservices.book.api.domain.Multiplication;
import microservices.book.api.domain.MultiplicationAttempt;
import microservices.book.api.domain.MultiplicationStatistic;
import microservices.book.api.services.multiplication.MultiplicationService;
import microservices.book.api.services.multiplication.RandomGeneratorService;
import microservices.book.api.services.persistent.Database;

@Service
final class MultiplicationServiceImpl implements MultiplicationService {

  private RandomGeneratorService rng;

  private Database db;

  @Autowired
  MultiplicationServiceImpl(RandomGeneratorService rng, Database db) {
    this.rng = rng;
    this.db = db;
  }

  @Override
  public Multiplication createRandomMultiplication() {
    int factorA = rng.generateRandomFactor();
    int factorB = rng.generateRandomFactor();
    return new Multiplication(factorA, factorB);
  }

  @Override
  public boolean checkAttempt(MultiplicationAttempt attempt) {
    boolean correct =
        attempt.getResult()
            == attempt.getMultiplication().getFactorA() * attempt.getMultiplication().getFactorB();
    db.saveAttempt(attempt, correct);
    return correct;
  }

  @Override
  public List<MultiplicationStatistic> getStatsForUser(String alias) {
    return db.getStats(alias);
  }
}
