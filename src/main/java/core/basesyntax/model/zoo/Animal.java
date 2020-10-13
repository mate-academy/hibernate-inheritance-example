package core.basesyntax.model.zoo;

import javax.persistence.*;

@Entity(name = "animals")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "species",
        discriminatorType = DiscriminatorType.STRING)
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int age;
    private String name;

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
}
