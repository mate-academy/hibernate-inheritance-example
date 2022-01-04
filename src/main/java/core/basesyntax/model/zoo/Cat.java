package core.basesyntax.model.zoo;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cats")
public class Cat extends Animal {
    private int numberOfLives;
    private String color;
}
