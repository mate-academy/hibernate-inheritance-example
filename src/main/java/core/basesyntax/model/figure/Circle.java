package core.basesyntax.model.figure;

import javax.persistence.Entity;

@Entity
public class Circle extends Figure {
    private Long id;
    private int radius;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{" + "id=" + id + ", radius=" + radius + '}';
    }
}
