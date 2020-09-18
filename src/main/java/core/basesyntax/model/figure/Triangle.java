package core.basesyntax.model.figure;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "triangle")
public class Triangle extends Figure {
    private double area;

    public Triangle() {
    }

    public Triangle(String color) {
        super(color);
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
