package microservices.book.api.domain;

import java.util.Objects;

public final class MultiplicationStatistic {

    private final String alias;

    private final int factorA;

    private final int factorB;

    private final int result;

    private final boolean correct;

    public MultiplicationStatistic(
            String alias, int factorA, int factorB, int result, boolean correct) {
        this.alias = alias;
        this.factorA = factorA;
        this.factorB = factorB;
        this.result = result;
        this.correct = correct;
    }

    protected MultiplicationStatistic() {
        this(null, -1, -1, -1, false);
    }

    public String getAlias() {
        return alias;
    }

    public int getFactorA() {
        return factorA;
    }

    public int getFactorB() {
        return factorB;
    }

    public int getResult() {
        return result;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(alias, correct, factorA, factorB, result);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MultiplicationStatistic other = (MultiplicationStatistic) obj;
        return Objects.equals(alias, other.alias)
                && correct == other.correct
                && factorA == other.factorA
                && factorB == other.factorB
                && result == other.result;
    }

    @Override
    public String toString() {
        return String.format(
                "MultiplicationStatistic [alias=%s, factorA=%s, factorB=%s, result=%s, correct=%s]",
                alias, factorA, factorB, result, correct);
    }
}
