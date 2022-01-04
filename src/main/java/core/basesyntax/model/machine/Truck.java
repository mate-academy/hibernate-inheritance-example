package core.basesyntax.model.machine;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "trucks")
public class Truck extends Machine {
    private String color;
    private double maxAllowedWeight;
}
