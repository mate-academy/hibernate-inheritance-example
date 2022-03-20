package core.basesyntax.model.ma;

import javax.persistence.Entity;

@Entity(name = "mentors")
public class Mentor extends Person {

    @Override
    public String toString() {
        return "Mentor{"
                + " age=" + getAge()
                + ", name=" + getName() + '\''
                + "}";
    }
}
