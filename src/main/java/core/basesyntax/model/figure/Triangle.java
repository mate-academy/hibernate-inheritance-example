package core.basesyntax.model.figure;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "triangles")
@PrimaryKeyJoinColumn(name = "triangle_id")
public class Triangle extends Figure {
    private double area;

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
