package core.basesyntax.model.machine;

import java.util.Objects;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Truck extends Machine {
    private String color;
    private double maxAllowedWeight;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return Double.compare(truck.maxAllowedWeight, maxAllowedWeight) == 0
                && Objects.equals(color, truck.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, maxAllowedWeight);
    }
}
