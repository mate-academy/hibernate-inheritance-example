package core.basesyntax.model.figure;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Figure {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long figureId;
    private String color;

    public Figure() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getId() {
        return figureId;
    }

    public void setId(Long figureId) {
        this.figureId = figureId;
    }
}
