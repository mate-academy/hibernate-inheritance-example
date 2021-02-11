package core.basesyntax.model.machine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car extends Machine {
    @Column(name = "horse_power")
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

    @Override
    public String toString() {
        return super.toString() + "Car{"
                + "horsePower=" + horsePower
                + ", model='" + model + '\''
                + '}';
    }
}
