package core.basesyntax.model.machine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "truck")
public class Truck extends Machine {
    private String color;
    @Column(name = "max_allowed_weight")
    private double maxAllowedWeight;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getMaxAllowedWeight() {
        return maxAllowedWeight;
    }

    public void setMaxAllowedWeight(double maxAllowedWeight) {
        this.maxAllowedWeight = maxAllowedWeight;
    }
}
