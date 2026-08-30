package core.basesyntax.model.figure;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "triangles")
public class Triangle extends Figure {
    private double area;
}
