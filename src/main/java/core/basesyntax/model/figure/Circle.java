package core.basesyntax.model.figure;

import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Circle extends Figure {
    private int radius;
}
