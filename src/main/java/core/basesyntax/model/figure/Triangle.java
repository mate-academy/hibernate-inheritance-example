package core.basesyntax.model.figure;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Triangle extends Figure {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private double area;

    public Triangle() {
    }

    public Triangle(double area) {
        this.area = area;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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
        if (!(o instanceof Triangle)) {
            return false;
        }
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.getArea(), getArea()) == 0
                && getId().equals(triangle.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getArea());
    }

    @Override
    public String toString() {
        return "Triangle{ id=" + id + ", area = " + area + "'}'";
    }
}
