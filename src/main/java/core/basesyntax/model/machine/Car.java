package core.basesyntax.model.machine;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "car_machine")
public class Car extends Machine {
    @Id
    private Long id;
    private int horsePower;
    private String model;

    public Long getId() {
        return id;
    }

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
}
