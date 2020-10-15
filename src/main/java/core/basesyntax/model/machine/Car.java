package core.basesyntax.model.machine;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Car extends Machine {
    @Column(name = "horse_power")
    private int horsePower;
    private String model;
}
