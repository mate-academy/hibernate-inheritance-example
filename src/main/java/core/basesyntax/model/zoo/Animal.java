package core.basesyntax.model.zoo;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Animal{ id= " + this.getId() + "id=" + id + ", age=" + age + ", name='" + name + '\'' + '}';
    }
}
