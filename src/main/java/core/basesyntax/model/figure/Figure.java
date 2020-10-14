package core.basesyntax.model.figure;

import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
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
