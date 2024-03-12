package core.basesyntax.model.ma;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "mentor_id")
public class Mentor extends Person {

}
