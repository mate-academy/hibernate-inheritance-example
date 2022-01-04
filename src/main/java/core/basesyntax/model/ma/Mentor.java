package core.basesyntax.model.ma;

import javax.persistence.Entity;

@Entity
public class Mentor extends Person {
    public Mentor(){
    }

    public Mentor(Long id, int age, String name) {
        super(id, age, name);
    }
}
