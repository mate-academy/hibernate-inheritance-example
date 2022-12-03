package core.basesyntax.model.figure;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Circle extends Figure {
    private int radius;
}
