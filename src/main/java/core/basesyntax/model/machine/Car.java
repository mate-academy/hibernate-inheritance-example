package core.basesyntax.model.machine;

import javax.persistence.Entity;

@Entity
public class Car extends Machine {
    private int horsePower;
    private String model;

    public Car() {
    }

    public Car(int horsePower, String model) {
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

    @Override
    public String toString() {
        return "Car { id " + super.getId()
                + ", maker " + super.getMaker()
                + ", model = '" + model
                + ", year = " + super.getYear()
                + ", horse power = " + horsePower + " '}";
    }
}
