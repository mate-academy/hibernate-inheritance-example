package core.basesyntax.model.zoo;

import jakarta.persistence.Entity;

@Entity
public class Cat extends Animal {

    private String color;
    private int numberOfLives;

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
                + "name='" + getName() + '\''
                + ", age=" + getAge()
                + ", color ='" + color + '\''
                + ", numberOfLives=" + numberOfLives
                + "}";
    }
}
