package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "mentor")
@PrimaryKeyJoinColumn(name = "id")
public class Mentor extends Person {

}
