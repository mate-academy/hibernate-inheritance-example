package core.basesyntax.model.zoo;

import javax.persistence.Entity;

@Entity(name = "dog_animal")
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
