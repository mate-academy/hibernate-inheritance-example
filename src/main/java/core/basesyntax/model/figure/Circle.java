package core.basesyntax.model.figure;

import java.util.Objects;
import javax.persistence.Entity;

@Entity
public class Circle extends Figure {
    private int radius;

    public Circle() {
    }

    public Circle(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRadius());
    }

    @Override
    public String toString() {
        return "Circle{ id= " + super.getId() + ", color = '" + super.getColor()
                + ", radius=" + radius + "'}'";
    }
}
