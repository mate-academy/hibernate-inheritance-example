package core.basesyntax.model.machine;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "trucks")
public class Truck extends Machine {
    private String color;
    private double maxAllowedWeight;
}
