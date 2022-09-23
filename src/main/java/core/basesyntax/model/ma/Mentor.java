package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity(name = "mentor")
@Table(name = "mentors")
public class Mentor extends Person {
    public enum Track {
        JAVA, FE, UI, QA
    }

    private int experience;
    @Enumerated(EnumType.STRING)
    private Coach.Track track;

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Coach.Track getTrack() {
        return track;
    }

    public void setTrack(Coach.Track track) {
        this.track = track;
    }
}
