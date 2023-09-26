package core.basesyntax.model.zoo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "dogs")
public class Dog extends Animal {
    private int tailLength;
    private String owner;

    public Dog() {
    }

    public int getTailLength() {
        return tailLength;
    }

    public void setTailLength(int tailLength) {
        this.tailLength = tailLength;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
