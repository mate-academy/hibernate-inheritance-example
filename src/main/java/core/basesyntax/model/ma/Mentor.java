package core.basesyntax.model.ma;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "mentors")
public class Mentor extends Person {
    // Mentor не має додаткових полів, тільки успадковані від Person
}
