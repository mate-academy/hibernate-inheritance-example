package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "coaches")
public class Coach extends Person {
    private int experience;
    private Track track;

    public enum Track {
        JAVA, FE, UI, QA
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
    public String toString() {
        return "Coach{"
                + "id=" + super.getId()
                + ", age=" + super.getAge()
                + ", name='" + super.getName()
                + ", experience=" + experience
                + ", track=" + track
                + '}';
    }
}
