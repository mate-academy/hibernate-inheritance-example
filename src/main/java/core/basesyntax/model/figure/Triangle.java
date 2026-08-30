package core.basesyntax.model.figure;

import jakarta.persistence.Entity;

@Entity
public class Triangle extends Figure {
    private int area;

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
}
