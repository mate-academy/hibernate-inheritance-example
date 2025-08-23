package core.basesyntax.model.figure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Triangle extends Figure {
    private double area;
    @Id
    private Long id;

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
