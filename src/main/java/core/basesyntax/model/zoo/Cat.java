package core.basesyntax.model.zoo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cats")
public class Cat extends Animal {
    private int numberOfLives;
    private String color;

    public Cat() {
    }

    public Cat(int age, String name, String color, int numberOfLives) {
        super(age, name);
        this.color = color;
        this.numberOfLives = numberOfLives;
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
}
