package core.basesyntax.model.figure;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Circle extends Figure {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Circle)) {
            return false;
        }
        Circle circle = (Circle) o;
        return getRadius() == circle.getRadius()
                && getId().equals(circle.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRadius());
    }

    @Override
    public String toString() {
        return "Circle{ id= " + id + ", radius=" + radius + "'}'";
    }
}
