package core.basesyntax.model.ma;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coaches")
public class Coach extends Person {
    public enum Track {
        JAVA, FE, UI, QA
    }

    @Id
    private Long id;
    private int experience;
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

    @Override
    public String toString() {
        return "Coach{"
                + "id=" + id
                + ", experience=" + experience
                + ", track=" + track
                + '}';
    }
}
