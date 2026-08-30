package core.basesyntax.model.figure;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "circles")
public class Circle extends Figure {
    private int radius;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{"
                + "radius=" + radius
                + '}' + super.toString();
    }
}
