package core.basesyntax.model.ma;

import javax.persistence.Entity;

@Entity
public class Mentor extends Person {

    public Mentor() {
    }

    public Mentor(int age, String name) {
        super(age, name);
    }
}
