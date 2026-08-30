package core.basesyntax.model.zoo;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
public class Dog extends Animal {
    private int tailLength;
    private String owner;
}
