package core.basesyntax.model.machine;

import javax.persistence.Entity;

@Entity
public class Truck extends Machine {
    private String color;
    private double maxAllowedWeight;

    public Truck() {
    }

    public Truck(String color, double maxAllowedWeight) {
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

    @Override
    public String toString() {
        return "Truck { id = " + super.getId()
                + ", maker " + super.getMaker()
                + ", color = '" + color
                + ", year = " + super.getYear()
                + ", Max allowed weight = " + maxAllowedWeight + " '}";
    }
}
