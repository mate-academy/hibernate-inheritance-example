package core.basesyntax.model.zoo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "cat")
@Setter
@Getter
@NoArgsConstructor
public class Cat extends Animal {
    private int numberOfLives;
    private String color;
}
