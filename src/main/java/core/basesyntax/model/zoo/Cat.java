package core.basesyntax.model.zoo;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Cat extends Animal {
    private int numberOfLives;
    private String color;
}
