package core.basesyntax.model.zoo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cat_animal")
public class Cat extends Animal {
    @Column(nullable = true)
    private int numberOfLives;
    @Column(nullable = true)
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
}
