package core.basesyntax.model.ma;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "coaches")
@PrimaryKeyJoinColumn(name = "coach_id")
public class Coach extends Person {
    private int experience;
    @Enumerated(EnumType.STRING)
    private Track track;

    public Coach() {
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

    public enum Track {
        JAVA, FE, UI, QA
    }
}
