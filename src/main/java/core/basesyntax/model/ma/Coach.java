package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "coach_id")
public class Coach extends Person {
    public enum Track {
        JAVA, FE, UI, QA
    }

    private int experience;
    @Enumerated(value = EnumType.STRING)
    private Track track;

    public Coach() {
    }

    public Coach(int experience, Track track) {
        this.experience = experience;
        this.track = track;
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
}
