package core.basesyntax.model.ma;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "mentors")
public class Mentor extends Person {
    @Override
    public String toString() {
        return "Mentor{"
                + "id=" + super.getId()
                + ", name=" + super.getName()
                + ", age=" + super.getAge()
                + '}';
    }
}
