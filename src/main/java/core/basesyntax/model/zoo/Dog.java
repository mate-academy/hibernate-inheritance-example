package core.basesyntax.model.zoo;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
@Entity
public class Dog extends Animal {
    private int tailLength;
    private String owner;
}
