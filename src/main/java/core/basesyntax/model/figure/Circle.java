package core.basesyntax.model.figure;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "circles")
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
}
