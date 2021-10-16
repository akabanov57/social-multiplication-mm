package microservices.book.impl.services.persistent;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import microservices.book.api.domain.MultiplicationAttempt;

@Entity
@Table(name = "multiplication_attempt")
@NamedQuery(name = "SELECT_ALL_ATTEMPTS", query = "SELECT a FROM MultiplicationAttemptE a")
class MultiplicationAttemptE extends Persistent {

    @Column(name = "factor_a", nullable = false)
    private int factorA;

    @Column(name = "factor_b", nullable = false)
    private int factorB;

    @Column(nullable = false)
    private int result;

    @Column(nullable = false)
    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserE user;

    MultiplicationAttemptE(int factorA, int factorB, int result, boolean correct) {
        id = null;
        this.factorA = factorA;
        this.factorB = factorB;
        this.result = result;
        this.correct = correct;
        user = null;
    }

    protected MultiplicationAttemptE() {
        this(-1, -1, -1, false);
    }

    static MultiplicationAttemptE from(MultiplicationAttempt attempt, boolean correct) {
        Objects.requireNonNull(attempt, "attempt is null.");
        return new MultiplicationAttemptE(
                attempt.getMultiplication().getFactorA(),
                attempt.getMultiplication().getFactorB(),
                attempt.getResult(),
                correct);
    }

    UserE getUser() {
        return user;
    }

    void setUser(UserE user) {
        this.user = user;
    }

    int getFactorA() {
        return factorA;
    }

    int getFactorB() {
        return factorB;
    }

    int getResult() {
        return result;
    }

    boolean isCorrect() {
        return correct;
    }

}
