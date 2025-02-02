package core.basesyntax.model.ma;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "mentors")
public class Mentor extends Person {
    /* private int experience;

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Mentor{" + super.toString()
                + ", experience=" + experience;
    } */
}
