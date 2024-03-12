package core.basesyntax.model.figure;

import jakarta.persistence.MappedSuperclass;

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
