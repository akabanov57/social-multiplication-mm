package microservices.book.impl.services.persistent;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

@MappedSuperclass
abstract class Persistent {

    private static final TimeBasedGenerator generator = Generators.timeBasedGenerator();

    @Id
    @Column(updatable = false, unique = true, nullable = false)
    protected UUID id;

    protected Persistent() {
        id = null;
    }

    @PrePersist
    void genId() {
        id = generator.generate();
    }

    UUID getId() {
        return id;
    }
}
