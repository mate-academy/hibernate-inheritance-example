package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
@Entity
public class Coach extends Person {
    private int experience;
    @Enumerated(EnumType.STRING)
    private Track track;

    public enum Track {
        JAVA, FE, UI, QA
    }
}
