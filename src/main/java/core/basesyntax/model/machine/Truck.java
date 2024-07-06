package core.basesyntax.model.machine;

import jakarta.persistence.Entity;

@Entity(name = "trucks")
public class Truck extends Machine {
    private String color;
    private double maxAllowedWeight;

    public Truck() {
    }

    public Truck(String color, double maxAllowedWeight) {
        super();
        this.color = color;
        this.maxAllowedWeight = maxAllowedWeight;
    }

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
