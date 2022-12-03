package core.basesyntax.model.machine;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Truck extends Machine {
    private String color;
    private double maxAllowedWeight;
}
