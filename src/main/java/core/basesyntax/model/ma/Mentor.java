package core.basesyntax.model.ma;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "mentors")
@PrimaryKeyJoinColumn(name = "mentor_id")
public class Mentor extends Person {
    public Mentor() {
    }
}
