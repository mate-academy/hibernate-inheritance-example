package core.basesyntax.model.figure;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "triangles")
public class Triangle extends Figure {
    private double area;

    public Triangle() {
    }

    public Triangle(String color, double area) {
        super(color);
        this.area = area;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Triangle{"
                + super.toString()
                + ", area=" + area
                + '}';
    }
}
