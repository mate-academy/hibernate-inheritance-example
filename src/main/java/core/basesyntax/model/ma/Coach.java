package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Coach extends Person {
    private Long id;

    public enum Track {
        JAVA, FE, UI, QA
    }

    private int experience;
    @Enumerated(EnumType.STRING)
    private Track track;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
