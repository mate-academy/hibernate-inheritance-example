package core.basesyntax.model.zoo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cats")
public class Cat extends Animal {
    private int numberOfLives;
    private String color;

    public int getNumberOfLives() {
        return numberOfLives;
    }

    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives = numberOfLives;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cat{"
                + "age=" + super.getAge()
                + ", name='" + super.getName()
                + "numberOfLives=" + numberOfLives
                + ", color='" + color + '\''
                + '}';
    }
}
