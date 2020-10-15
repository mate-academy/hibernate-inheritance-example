package core.basesyntax.model.ma;

import javax.persistence.Entity;

@Entity
public class Mentor extends Person {

    @Override
    public String toString() {
        return "Mentor{ id = " + super.getId()
                + ", name = " + super.getName()
                + ", age = " + super.getAge() + " }";
    }
}
