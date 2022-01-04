package core.basesyntax.model.figure;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "triangles")
public class Triangle extends Figure {
    private double area;
}
