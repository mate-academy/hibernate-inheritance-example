package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "coaches")
public class Coach extends Person {
    public enum Track {
        JAVA, FE, UI, QA
    }

    private int experience;
    @Enumerated(EnumType.STRING)
    private Track track;

    public Coach() {
        super();
    }

    public Coach(int age, String name, int experience, Track track) {
        super(age, name);
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
    public String toString() {
        return "Coach{"
                + "id=" + getId()
                + ", age=" + getAge()
                + ", name='" + getName() + '\''
                + ", experience=" + experience
                + ", track=" + track
                + '}';
    }
}
