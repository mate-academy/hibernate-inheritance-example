package core.basesyntax.model.ma;

import javax.persistence.Entity;

@Entity
public class Mentor extends Person {
    @Override
    public String toString() {
        return "Mentor{"
                + super.toString()
                + "}";
    }
}
