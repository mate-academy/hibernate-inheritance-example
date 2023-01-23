package core.basesyntax.model.machine;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Truck extends Machine {
    private String color;
    private double maxAllowedWeight;
}
