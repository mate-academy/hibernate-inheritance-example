package core.basesyntax.model.ma;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Coach extends Person {
    public enum Track {
        JAVA, FE, UI, QA
    }

    private int experience;
    private Track track;

    public Coach() {
    }

    public Coach(int experience, Track track) {
        this.experience = experience;
        this.track = track;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coach)) {
            return false;
        }
        Coach coach = (Coach) o;
        return getExperience() == coach.getExperience()
                && getTrack() == coach.getTrack();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExperience(), getTrack());
    }

    @Override
    public String toString() {
        return "Coach{ id = " + super.getId()
                + ", name = " + super.getName()
                + ", age = " + super.getAge()
                + ", experience = " + experience
                + ", track = '" + track + "' }";
    }
}
