package core.basesyntax.model.figure;

import jakarta.persistence.Entity;

@Entity(name = "circle")
public class Circle extends Figure {
    private int radius;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
