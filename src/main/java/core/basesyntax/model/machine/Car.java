package core.basesyntax.model.machine;

import javax.persistence.Entity;

@Entity(name = "car")
public class Car extends Machine {
    private int horsePower;
    private String model;

    public Car() {
    }

    public Car(int year, String maker, int horsePower, String model) {
        super(year, maker);
        this.horsePower = horsePower;
        this.model = model;
    }

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
