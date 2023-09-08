package core.basesyntax.model.ma;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "mentors")
public class Mentor extends Person {
    public Mentor() {
    }

    @Override
    public String toString() {
        return "Mentor{"
                + '}';
    }
}
