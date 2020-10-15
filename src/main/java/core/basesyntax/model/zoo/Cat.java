package core.basesyntax.model.zoo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cats")
@DiscriminatorValue("cat")
public class Cat extends Animal {
    private int numberOfLives;
    private String color;
}
