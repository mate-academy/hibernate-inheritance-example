package core.basesyntax.model.zoo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("dog")
public class Dog extends Animal {
    private int tailLength;
    private String owner;

    public Dog() {
    }

    public Dog(int age, String name, int tailLength, String owner) {
        super(age, name);
        this.tailLength = tailLength;
        this.owner = owner;
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
