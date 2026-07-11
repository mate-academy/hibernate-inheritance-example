package core.basesyntax.model.figure;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;

@Entity
@MappedSuperclass
public class Figure {
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
