package core.basesyntax.model.ma;

import jakarta.persistence.Entity;

@Entity
public class Mentor extends Person {
    private Long id;
    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
