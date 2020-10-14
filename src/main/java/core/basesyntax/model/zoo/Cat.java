package core.basesyntax.model.zoo;

import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cat")
public class Cat extends Animal {
    private int numberOfLives;
    private String color;

    public Cat(){
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
        return "Cat { numberOfLives = " + numberOfLives
                + ", color='" + color + "'}'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cat)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Cat cat = (Cat) o;
        return getNumberOfLives() == cat.getNumberOfLives()
                && getColor().equals(cat.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNumberOfLives(), getColor());
    }
}
