package core.basesyntax.model.animal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dog extends Animal {
    @Id
    private Long id;
    @Column(name = "tail_length")
    private int tailLength;
    private String owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Dog{"
                + "id=" + id
                + ", tailLength=" + tailLength
                + ", owner='" + owner + '\''
                + '}';
    }
}
