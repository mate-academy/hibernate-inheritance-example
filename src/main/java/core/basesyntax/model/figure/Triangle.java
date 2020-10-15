package core.basesyntax.model.figure;

import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Triangle extends Figure {
    private double area;
}
