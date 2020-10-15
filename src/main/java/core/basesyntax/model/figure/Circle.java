package core.basesyntax.model.figure;

import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Circle extends Figure {
    private int radius;
}
