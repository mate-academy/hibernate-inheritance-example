package core.basesyntax.model.figure;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "triangles")
public class Triangle extends Figure {
    @Id
    private Long id;
    private double area;

    @Override
    public String toString() {
        return "Triangle{"
                + "id=" + id
                + ", area=" + area
                + '}';
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
}
