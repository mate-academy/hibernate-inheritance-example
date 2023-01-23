package core.basesyntax.model.machine;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Car extends Machine {
    private int horsePower;
    private String model;
}
