package core.basesyntax.model.machine;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Truck extends Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
