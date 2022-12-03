package core.basesyntax.model.zoo;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Dog extends Animal {
    private int tailLength;
    private String owner;
}
