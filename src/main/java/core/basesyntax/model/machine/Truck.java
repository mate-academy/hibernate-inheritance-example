package core.basesyntax.model.machine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "trucks")
public class Truck extends Machine {
    @Column(name = "color")
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
