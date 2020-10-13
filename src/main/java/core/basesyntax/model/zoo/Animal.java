package core.basesyntax.model.zoo;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "zoo")
@DiscriminatorColumn(name = "animal_type",
        discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long animalId;
    private int age;
    private String name;

    public long getProductId() {
        return animalId;
    }

    public void setProductId(long animalId) {
        this.animalId = animalId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{"
                + "productId=" + animalId
                + ", age=" + age
                + ", name='" + name + '\''
                + '}';
    }
}
