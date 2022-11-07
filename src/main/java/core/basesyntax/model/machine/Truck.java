package core.basesyntax.model.machine;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "trucks")
public class Truck extends Machine {
    private String color;
    private double maxAllowedWeight;

    public Truck() {
        super();
    }

    public Truck(int year, String maker, String color, double maxAllowedWeight) {
        super(year, maker);
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
        return "Truck{"
                + "id=" + getId()
                + ", year=" + getYear()
                + ", maker='" + getMaker() + '\''
                + ", color='" + color + '\''
                + ", maxAllowedWeight=" + maxAllowedWeight
                + '}';
    }
}
