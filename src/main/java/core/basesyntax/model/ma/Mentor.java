package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Mentor extends Person {

    @Override
    public String toString() {
        return "Mentor{ id = " + super.getId()
                + ", name = " + super.getName()
                + ", age = " + super.getAge() + " }";
    }
}
