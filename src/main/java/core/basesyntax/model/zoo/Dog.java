package core.basesyntax.model.zoo;

import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Dog dog = (Dog) o;
        return tailLength == dog.tailLength
                && Objects.equals(owner, dog.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tailLength, owner);
    }

    @Override
    public String toString() {
        return "Dog{"
                + "id=" + super.getId()
                + ", name=" + super.getName()
                + ", age=" + super.getAge()
                + ", tailLength=" + tailLength
                + ", owner='" + owner + '\''
                + '}';
    }
}
