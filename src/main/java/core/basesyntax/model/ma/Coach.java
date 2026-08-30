package core.basesyntax.model.ma;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Coach extends Person {
    private int experience;
    @Enumerated(EnumType.STRING)
    private Track track;

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public enum Track {
        JAVA, FE, UI, QA
    }
}
