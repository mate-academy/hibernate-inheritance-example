package core.basesyntax.model.zoo;

import javax.persistence.Entity;

@Entity
public class Cat extends Animal {
    private int numberOfLives;
    private String color;

    public Cat() {
    }

    public Cat(Long id, int age, String name, int numberOfLives, String color) {
        super(id, age, name);
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
}
