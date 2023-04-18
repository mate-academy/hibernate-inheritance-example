package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Mentor extends Person {
    public enum GroupDirection {
        JAVA, FE, UI, QA
    }

    private int age;
    private int experience;
    private int countOfStudents;
    @Enumerated(EnumType.STRING)
    private Mentor.GroupDirection group;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getCountOfStudents() {
        return countOfStudents;
    }

    public void setCountOfStudents(int countOfStudents) {
        this.countOfStudents = countOfStudents;
    }

    public Mentor.GroupDirection getGroupDirection() {
        return group;
    }

    public void setGroupDirection(Mentor.GroupDirection group) {
        this.group = group;
    }
}
