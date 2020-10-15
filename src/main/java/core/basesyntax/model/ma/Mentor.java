package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "mentor_Id")
public class Mentor extends Person {

}
