package core.basesyntax.model.figure;

import jakarta.persistence.Entity;

@Entity
public class Circle extends Figure {
    private Integer radius;

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }
}
