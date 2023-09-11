package core.basesyntax.model.ma;

import jakarta.persistence.Entity;

@Entity
public class Mentor extends Person {
    public Mentor() {
    }

    public Mentor(int age, String name) {
        super(age, name);
    }

    @Override
    public String toString() {
        return "Person{"
                + "id=" + getId()
                + ", age=" + getAge()
                + ", name='" + getName() + '\''
                + '}';
    }
}
