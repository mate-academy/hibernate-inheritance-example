package core.basesyntax.model.ma;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Mentor extends Person {
    public enum Track {
        JAVA, FE, UI, QA
    }

    @Enumerated(EnumType.STRING)
    private Track track;

    public Mentor() {
    }

    public Mentor(Track track, String name, int age) {
        super(age, name);
        this.track = track;
    }
}
