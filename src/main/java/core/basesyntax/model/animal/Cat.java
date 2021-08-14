package core.basesyntax.model.animal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cat extends Animal {
    @Id
    private Long id;
    @Column(name = "number_of_lives")
    private int numberOfLives;
    private String color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                + "id=" + id
                + ", numberOfLives=" + numberOfLives
                + ", color='" + color + '\''
                + '}';
    }
}
