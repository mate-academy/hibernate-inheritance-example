package core.basesyntax.model.machine;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class Truck extends Machine {
    private String color;
    @Column(name = "max_allowed_weight")
    private double maxAllowedWeight;
}
