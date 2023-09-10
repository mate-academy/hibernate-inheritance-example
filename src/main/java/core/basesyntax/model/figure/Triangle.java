package core.basesyntax.model.figure;

import jakarta.persistence.Entity;

@Entity(name = "triangles")
public class Triangle extends Figure {
    private double area;

    public Triangle() {
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
