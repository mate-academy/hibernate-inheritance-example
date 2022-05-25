package core.basesyntax.model.ma;

import javax.persistence.Entity;

@Entity
public class Mentor extends Person {
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
