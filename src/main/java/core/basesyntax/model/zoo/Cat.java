package core.basesyntax.model.zoo;

import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Cat cat = (Cat) o;
        return numberOfLives == cat.numberOfLives
                && Objects.equals(color, cat.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfLives, color);
    }

    @Override
    public String toString() {
        return "Cat{"
                + "id=" + super.getId()
                + ", name=" + super.getName()
                + ", age=" + super.getAge()
                + ", numberOfLives=" + numberOfLives
                + ", color='" + color + '\''
                + '}';
    }
}
