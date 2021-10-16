package microservices.book.api.domain;

import java.util.Objects;

public final class User {

    private final String alias;

    /** Empty constructor for JSON (de)serialization */
    protected User() {
        alias = null;
    }

    public User(final String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public int hashCode() {
        return Objects.hash(alias);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return Objects.equals(alias, other.alias);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [alias=").append(alias).append("]");
        return builder.toString();
    }

}
