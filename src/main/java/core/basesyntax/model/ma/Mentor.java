package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "mentor")
@Table(name = "mentors")
public class Mentor extends Person {
    public Mentor() {
    }

    public Mentor(int age, String name) {
        super(age, name);
    }
}
