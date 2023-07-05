package core.basesyntax.model.figure;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Triangle extends Figure {
    private double area;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
