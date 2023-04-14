package core.basesyntax.model.figure;

import javax.persistence.Entity;

@Entity(name = "figures")
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
                + super.toString()
                + "radius=" + radius
                + '}';
    }
}
