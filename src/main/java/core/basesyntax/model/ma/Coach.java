package core.basesyntax.model.ma;

import java.util.Objects;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Coach extends Person {
    public enum Track {
        JAVA, FE, UI, QA
    }

    private int experience;
    private Track track;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coach coach = (Coach) o;
        return experience == coach.experience
                && track == coach.track;
    }

    @Override
    public int hashCode() {
        return Objects.hash(experience, track);
    }
}
