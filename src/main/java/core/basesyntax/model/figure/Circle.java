package core.basesyntax.model.figure;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Circle extends Figure {
    private int radius;

}
