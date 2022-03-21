package core.basesyntax.model.figure;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
@Entity
public class Circle extends Figure {
    private int radius;
}
