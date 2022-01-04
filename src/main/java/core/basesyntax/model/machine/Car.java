package core.basesyntax.model.machine;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends Machine {
    private int horsePower;
    private String model;
}
