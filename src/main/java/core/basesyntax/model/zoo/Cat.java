package core.basesyntax.model.zoo;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("CAT")
public class Cat extends Animal {
    @Column(name = "number_of_lives")
    private int numberOfLives;
    private String color;
}
