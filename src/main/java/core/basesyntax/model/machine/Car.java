package core.basesyntax.model.machine;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car extends Machine {
    @Id
    private Long id;
    private int horsePower;
    private String model;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
        return "Car{"
                + "id=" + id
                + ", year=" + super.getYear()
                + ", maker='" + super.getMaker()
                + ", horsePower=" + horsePower
                + ", model='" + model + '\''
                + '}';
    }
}
