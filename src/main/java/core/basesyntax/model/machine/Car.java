package core.basesyntax.model.machine;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "cars")
public class Car extends Machine {
    private int horsePower;
    private String model;
}
