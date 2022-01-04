package core.basesyntax.model.zoo;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dogs")
public class Dog extends Animal {
    private int tailLength;
    private String owner;
}
