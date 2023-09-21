package core.basesyntax.model.figure;

import jakarta.persistence.Entity;
import java.util.Objects;

@Entity(name = "triangle")
public class Triangle extends Figure {
    private double area;

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.area, area) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(area);
    }
}
