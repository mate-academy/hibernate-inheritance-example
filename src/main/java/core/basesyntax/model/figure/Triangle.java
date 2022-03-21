package core.basesyntax.model.figure;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
@Entity
public class Triangle extends Figure {
    private double area;
}
