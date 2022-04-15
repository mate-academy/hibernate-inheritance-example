package core.basesyntax.model.ma;

import javax.persistence.Entity;

@Entity
public class Mentor extends Person {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int experience) {
        this.age = experience;
    }
}
