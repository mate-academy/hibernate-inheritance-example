package core.basesyntax.model.zoo;

import javax.persistence.Entity;

@Entity
public class Cat extends Animal {
    private int numberOfLives;
    private String color;

    public Cat() {
    }

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
                + "numberOfLives=" + numberOfLives
                + ", color='" + color + '\''
                + '}';
    }
}
