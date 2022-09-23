package core.basesyntax.model.figure;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "triangle")
@Table(name = "triangles")
public class Triangle extends Figure {
    private double area;

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
