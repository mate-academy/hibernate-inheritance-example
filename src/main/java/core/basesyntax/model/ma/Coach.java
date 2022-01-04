package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "coaches")
public class Coach extends Person {
    public enum Track {
        JAVA, FE, UI, QA
    }

    private int experience;
    @Enumerated(EnumType.STRING)
    private Track track;

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
