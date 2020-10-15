package core.basesyntax.model.zoo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dogs")
@DiscriminatorValue("dog")
public class Dog extends Animal {
    private int tailLength;
    private String owner;
}
