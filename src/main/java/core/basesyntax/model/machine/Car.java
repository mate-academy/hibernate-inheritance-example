package core.basesyntax.model.machine;

import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Car extends Machine {
    private int horsePower;
    private String model;
}
