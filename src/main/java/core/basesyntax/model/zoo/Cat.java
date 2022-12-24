package core.basesyntax.model.zoo;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cats")
public class Cat extends Animal {
    private int numberOfLives;
    private String color;
}
