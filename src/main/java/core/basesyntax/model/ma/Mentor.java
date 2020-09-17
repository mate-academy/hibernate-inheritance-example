package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mentor")
public class Mentor extends Person {

    public Mentor() {
    }

    public Mentor(Integer age) {
        super(age);
    }

    @Override
    public String toString() {
        return "Mentor{" + "age= " + getAge() + '}';
    }
}
