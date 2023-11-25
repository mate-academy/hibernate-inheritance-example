package core.basesyntax.model.zoo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(Animal.ID_CAT)
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
                + "id=" + getId()
                + ", age=" + getAge()
                + ", name='" + getName() + '\''
                + ", numberOfLives=" + numberOfLives
                + ", color='" + color + '\''
                + '}';
    }
}
