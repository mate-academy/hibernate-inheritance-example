package core.basesyntax.model.figure;

import javax.persistence.Entity;

@Entity
public class Triangle extends Figure {
    private double area;

    public Triangle() {
    }

    public Triangle(Long id, String color, double area) {
        super(id, color);
        this.area = area;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
