package core.basesyntax.model.zoo;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Dog extends Animal {
    private int tailLength;
    private String owner;
}
