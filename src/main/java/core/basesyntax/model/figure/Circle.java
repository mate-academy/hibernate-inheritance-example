package core.basesyntax.model.figure;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "circles")
public class Circle extends Figure {
    private int radius;
}
