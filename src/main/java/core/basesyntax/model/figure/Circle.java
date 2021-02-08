package core.basesyntax.model.figure;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Circle extends Figure {
    @Id
    private int radius;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
