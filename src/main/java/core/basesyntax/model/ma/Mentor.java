package core.basesyntax.model.ma;

import jakarta.persistence.*;

@Entity
@Table
public class Mentor extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
