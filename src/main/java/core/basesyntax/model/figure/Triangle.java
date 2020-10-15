package core.basesyntax.model.figure;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Triangle extends Figure {
    @Id
    private Long id;
    private double area;

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
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
