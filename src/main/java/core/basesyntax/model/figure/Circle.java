package core.basesyntax.model.figure;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "circles")
public class Circle extends Figure {
    private int radius;

    public Circle() {
        super();
    }

    public Circle(String color, int radius) {
        super(color);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{"
                + "id=" + getId()
                + ", color='" + getColor() + "\'"
                + ", radius=" + radius
                + '}';
    }
}
