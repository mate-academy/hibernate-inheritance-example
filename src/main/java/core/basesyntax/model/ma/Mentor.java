package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mentors")
public class Mentor extends Person {
    private String name;
    private int age;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }
}
