package core.basesyntax.model.zoo;

import javax.persistence.Entity;

@Entity(name = "cats")
public class Cat extends Animal {
    private int numberOfLives;
    private String color;

    public Cat() {
    }

    public Cat(int numberOfLives, String color) {
        this.numberOfLives = numberOfLives;
        this.color = color;
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
