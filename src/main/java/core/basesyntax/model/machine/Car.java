package core.basesyntax.model.machine;

import java.util.Objects;
import javax.persistence.Entity;

@Entity
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Car car = (Car) o;
        return horsePower == car.horsePower
                && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), horsePower, model);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + super.getId()
                + ", maker=" + super.getMaker()
                + ", year=" + super.getYear()
                + ", horsePower=" + horsePower
                + ", model='" + model + '\''
                + '}';
    }
}
