package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "mentors")
@PrimaryKeyJoinColumn(name = "mentor_id")
public class Mentor extends Person {

}
