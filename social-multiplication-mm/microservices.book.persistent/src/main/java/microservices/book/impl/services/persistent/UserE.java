package microservices.book.impl.services.persistent;

import static javax.persistence.CascadeType.ALL;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import microservices.book.api.domain.User;

@Entity
@Table(name = "user_e")
@NamedQueries({
    @NamedQuery(name = "DELETE_ALL_USERS", query = "DELETE FROM UserE"),
    @NamedQuery(name = "SELECT_ALL_USERS", query = "SELECT u FROM UserE u"),
    @NamedQuery(name = "FIND_BY_ALIAS", query = "SELECT u FROM UserE u WHERE u.alias = :ualias"),
    @NamedQuery(
            name = "GET_STATISTICS",
            query =
                    "SELECT new microservices.book.api.domain.MultiplicationStatistic(u.alias,m.factorA,m.factorB,m.result,m.correct) FROM UserE u JOIN u.attempts m WHERE u.alias = :ualias")
})
class UserE extends Persistent {

    @Column(name = "u_alias", nullable = false)
    private String alias;

    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
    private List<MultiplicationAttemptE> attempts;

    UserE(String alias) {
        id = null;
        this.alias = alias;
    }

    protected UserE() {
        this(null);
    }

    static UserE from(User user) {
        Objects.requireNonNull(user, "user is null.");
        return new UserE(user.getAlias());
    }

    String getAlias() {
        return alias;
    }

    void setAlias(String alias) {
        this.alias = alias;
    }

    List<MultiplicationAttemptE> getAttempts() {
        return attempts != null ? Collections.unmodifiableList(attempts) : Collections.emptyList();
    }

    UserE addAttempt(MultiplicationAttemptE attempt) {
        if (attempts == null) {
            attempts = new LinkedList<>();
        }
        attempts.add(attempt);
        attempt.setUser(this);
        return this;
    }

    UserE removeAttempt(MultiplicationAttemptE attempt) {
        if (attempts != null) {
            attempts.remove(attempt);
        }
        return this;
    }

}
