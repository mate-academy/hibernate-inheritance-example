package core.basesyntax.model.zoo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "dog")
@Setter
@Getter
@NoArgsConstructor
public class Dog extends Animal {
    private int tailLength;
    private String owner;
}
