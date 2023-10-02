package core.basesyntax.model.figure;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "circles")
public class Circle extends Figure {
    private int radius;
}
