package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mentor extends Person {

    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
