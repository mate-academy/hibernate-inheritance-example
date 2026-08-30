package core.basesyntax.model.ma;

import jakarta.persistence.Entity;

@Entity
public class Mentor extends Person {
    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
