package core.basesyntax.model.figure;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Triangle extends Figure {
    @Id
    private double area;

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
