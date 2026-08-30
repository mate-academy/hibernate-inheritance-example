package core.basesyntax.model.ma;

import jakarta.persistence.Entity;

@Entity
public class Mentor extends Person {
    @Override
    public Long getId() {
        return super.getId();
    }
}
