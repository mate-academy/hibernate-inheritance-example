package core.basesyntax.model.ma;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "mentor")
public class Mentor extends Person {

    public Mentor() {
    }

    public Mentor(int age, String name) {
        super(age, name);
    }
}
