package core.basesyntax.model.machine;

import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Truck extends Machine {
    private String color;
    private double maxAllowedWeight;
}
