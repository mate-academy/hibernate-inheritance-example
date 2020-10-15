package core.basesyntax.model.figure;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@MappedSuperclass
public class Figure {
    private String color;
}
