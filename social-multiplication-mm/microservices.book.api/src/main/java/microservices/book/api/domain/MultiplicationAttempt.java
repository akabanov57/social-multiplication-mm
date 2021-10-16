package microservices.book.api.domain;

import java.util.Objects;

public final class MultiplicationAttempt {

    private final User user;

    private final Multiplication multiplication;

    private final int result;

    /** Empty constructor for JSON (de)serialization */
    protected MultiplicationAttempt() {
        user = null;
        multiplication = null;
        result = -1;
    }

    public MultiplicationAttempt(
            final User user, final Multiplication multiplication, int result) {
        this.user = user;
        this.multiplication = multiplication;
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public Multiplication getMultiplication() {
        return multiplication;
    }

    public int getResult() {
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(multiplication, result, user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MultiplicationAttempt other = (MultiplicationAttempt) obj;
        return Objects.equals(multiplication, other.multiplication)
                && result == other.result
                && Objects.equals(user, other.user);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MultiplicationAttempt [user=")
                .append(user)
                .append(", multiplication=")
                .append(multiplication)
                .append(", result=")
                .append(result)
                .append("]");
        return builder.toString();
    }
}
