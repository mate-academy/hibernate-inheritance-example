package core.basesyntax.model.figure;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Triangle extends Figure {
    private double area;
}
