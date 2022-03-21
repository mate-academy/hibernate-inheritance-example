package core.basesyntax.model.zoo;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
@Entity
public class Cat extends Animal {
    private int numberOfLives;
    private String color;
}
