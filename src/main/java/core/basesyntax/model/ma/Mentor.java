package core.basesyntax.model.ma;

import javax.persistence.Entity;

@Entity
public class Mentor extends Person {
    private int limitOfStudents;

    public int getLimitOfStudents() {
        return limitOfStudents;
    }

    public void setLimitOfStudents(int limitOfStudents) {
        this.limitOfStudents = limitOfStudents;
    }
}
