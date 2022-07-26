package core.basesyntax.model.machine;

import javax.persistence.Entity;

@Entity(name = "trucks")
public class Truck extends Machine {
    private String color;
    private double maxAllowedWeight;

    public Truck() {
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

    @Override
    public String toString() {
        return "Truck{"
                + "color='" + color + '\''
                + ", maxAllowedWeight=" + maxAllowedWeight
                + '}';
    }
}
