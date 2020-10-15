package core.basesyntax.model.machine;

import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Car extends Machine {
    private int horsePower;
    private String model;
}
