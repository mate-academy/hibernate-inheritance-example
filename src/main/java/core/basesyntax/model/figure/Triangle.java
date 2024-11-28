package core.basesyntax.model.figure;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Triangle extends Figure {
    private double area;

}
