package core.basesyntax.model.ma;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "coaches")
public class Coach extends Person {
    public enum Track {
        JAVA, FE, UI, QA
    }

    @Column
    private int experience;
    @Column
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
}
