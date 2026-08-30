package core.basesyntax.model.figure;

import jakarta.persistence.Entity;

@Entity(name = "triangle")
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
}
