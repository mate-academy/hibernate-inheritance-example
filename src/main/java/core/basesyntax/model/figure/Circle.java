package core.basesyntax.model.figure;

import javax.persistence.Entity;

@Entity
public class Circle extends Figure {
    private Long id;
    private int radius;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
