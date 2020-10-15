package core.basesyntax.model.ma;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Data
@NoArgsConstructor
public class Coach extends Person {
    public enum Track {
        JAVA, FE, UI, QA
    }

    private int experience;
    private Track track;
}
