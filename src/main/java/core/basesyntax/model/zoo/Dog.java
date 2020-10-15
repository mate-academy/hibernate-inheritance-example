package core.basesyntax.model.zoo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("dog")
public class Dog extends Animal {
    private int tailLength;
    private String owner;
}
