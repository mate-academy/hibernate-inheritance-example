package core.basesyntax.model.figure;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Triangle extends Figure {
    private double area;
}
