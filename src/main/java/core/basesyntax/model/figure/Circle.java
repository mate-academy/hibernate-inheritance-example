package core.basesyntax.model.figure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Circle extends Figure {
    private int radius;
    @Id
    private Long id;

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
}
