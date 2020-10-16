package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "mentor_id")
public class Mentor extends Person {
    @Override
    public String toString() {
        return "Mentor{} id= " + this.getId();
    }
}
