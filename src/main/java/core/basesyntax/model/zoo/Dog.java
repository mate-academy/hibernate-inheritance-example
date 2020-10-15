package core.basesyntax.model.zoo;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue("DOG")
public class Dog extends Animal {
    @Column(name = "tail_length")
    private int tailLength;
    private String owner;
}
