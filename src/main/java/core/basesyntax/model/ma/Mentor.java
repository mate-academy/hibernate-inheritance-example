package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mentors")
public class Mentor extends Person {
    @Override
    public String toString() {
        return "Mentor{"
                + "id=" + super.getId()
                + ", age=" + super.getAge()
                + ", name='" + super.getName()
                + '}';
    }
}
