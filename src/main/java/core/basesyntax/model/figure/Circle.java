package core.basesyntax.model.figure;

import jakarta.persistence.Entity;

@Entity(name = "circles")
public class Circle extends Figure {
    private int radius;

    public Circle() {
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
        return "Figure{"
                + "id=" + getId()
                + ", color='" + getColor()
                + " Circle{"
                + "radius=" + radius
                + '}'
                + '}';
    }
}
