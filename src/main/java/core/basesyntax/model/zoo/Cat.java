package core.basesyntax.model.zoo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("cat")
public class Cat extends Animal {
    private int numberOfLives;
    private String color;
}
