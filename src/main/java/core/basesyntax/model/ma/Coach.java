package core.basesyntax.model.ma;

import java.util.Objects;
import javax.persistence.Entity;

@Entity
public class Coach extends Person {
    public enum Track {
        JAVA, FE, UI, QA
    }

    private int experience;
    private Track track;

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
    public String toString() {
        return "Coach{"
                + "experience=" + experience
                + ", track=" + track
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coach coach = (Coach) o;
        return experience == coach.experience
                && track == coach.track;
    }

    @Override
    public int hashCode() {
        return Objects.hash(experience, track);
    }
}
