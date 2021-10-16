package microservices.book.api.domain;

import java.util.Objects;

public final class Multiplication {
    // Both factors
    private final int factorA;

    private final int factorB;

    /** Empty constructor for JSON (de)serialization */
    protected Multiplication() {
        this(0, 0);
    }

    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
    }

    public int getFactorA() {
        return factorA;
    }

    public int getFactorB() {
        return factorB;
    }

    @Override
    public int hashCode() {
        return Objects.hash(factorA, factorB);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Multiplication other = (Multiplication) obj;
        return factorA == other.factorA && factorB == other.factorB;
    }

    @Override
    public String toString() {
        return String.format("Multiplication [factorA=%d, factorB=%d]", factorA, factorB);
    }
}
