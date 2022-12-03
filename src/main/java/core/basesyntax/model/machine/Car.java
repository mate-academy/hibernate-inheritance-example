package core.basesyntax.model.machine;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car extends Machine {
    private int horsePower;
    private String model;
}
