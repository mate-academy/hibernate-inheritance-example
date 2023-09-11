package core.basesyntax.model.ma;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Coach extends Person {
    private int experience;
    @Enumerated(EnumType.STRING)
    private Track track;

    public Coach() {
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
        return "Person{"
                + "id=" + getId()
                + ", age=" + getAge()
                + ", name='" + getName()
                + "', Coach{"
                + "experience=" + experience
                + ", track=" + track
                + '}' + '}';
    }

    public enum Track {
        JAVA, FE, UI, QA
    }
}
