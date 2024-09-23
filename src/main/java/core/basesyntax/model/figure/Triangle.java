package core.basesyntax.model.figure;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class Triangle extends Figure {
    private double area;

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
