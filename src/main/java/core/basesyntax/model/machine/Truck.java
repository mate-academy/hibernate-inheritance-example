package core.basesyntax.model.machine;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trucks")
public class Truck extends Machine {
    @Id
    private Long id;
    private String color;
    private double maxAllowedWeight;

    @Override
    public Long getId() {
        return id;
    }

    @Override
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

    @Override
    public String toString() {
        return "Truck{"
                + "id=" + id
                + ", year=" + super.getYear()
                + ", maker='" + super.getMaker()
                + ", color='" + color + '\''
                + ", maxAllowedWeight=" + maxAllowedWeight
                + '}';
    }
}
