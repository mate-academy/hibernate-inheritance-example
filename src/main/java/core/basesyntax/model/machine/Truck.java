package core.basesyntax.model.machine;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "truck_machine")
public class Truck extends Machine {
    @Id
    private Long id;
    private String color;
    private double maxAllowedWeight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
