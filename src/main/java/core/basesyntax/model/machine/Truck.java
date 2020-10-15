package core.basesyntax.model.machine;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Truck extends Machine {
    private String color;
    @Column(name = "max_allowed_weight")
    private double maxAllowedWeight;
}
