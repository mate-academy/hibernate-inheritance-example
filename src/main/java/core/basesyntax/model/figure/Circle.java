package core.basesyntax.model.figure;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Circle extends Figure {
    @Id
    private Long id;
    private int radius;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
