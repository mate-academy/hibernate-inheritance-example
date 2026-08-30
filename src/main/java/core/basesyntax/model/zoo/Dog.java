package core.basesyntax.model.zoo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(Animal.ID_DOG)
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
                + "id=" + getId()
                + ", age=" + getAge()
                + ", name='" + getName() + '\''
                + ", tailLength=" + tailLength
                + ", owner='" + owner + '\''
                + '}';
    }
}
