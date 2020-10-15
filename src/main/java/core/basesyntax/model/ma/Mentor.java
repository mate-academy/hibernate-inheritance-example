package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "mentor_id")
@Table(name = "mentors")
public class Mentor extends Person {

}
