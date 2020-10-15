package core.basesyntax.model.zoo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Dog")
public class Dog extends Animal {
    private int tailLength;
    private String owner;

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

    @Override
    public String toString() {
        return "Dog{"
                + "tailLength=" + tailLength
                + ", owner='" + owner + '\''
                + '}';
    }
}
