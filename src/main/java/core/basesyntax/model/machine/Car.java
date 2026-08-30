package core.basesyntax.model.machine;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "car")
public class Car extends Machine {
    private int horsePower;
    private String model;

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
