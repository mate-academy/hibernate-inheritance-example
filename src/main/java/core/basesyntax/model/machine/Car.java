package core.basesyntax.model.machine;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class Car extends Machine {
    @Column(name = "horse_power")
    private int horsePower;
    private String model;
}
