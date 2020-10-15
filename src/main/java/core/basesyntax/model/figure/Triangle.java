package core.basesyntax.model.figure;

import javax.persistence.Entity;

@Entity
public class Triangle extends Figure {
    private Long id;
    private double area;

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Triangle{" + "id=" + id + ", area=" + area + '}';
    }
}
