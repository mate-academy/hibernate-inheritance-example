package core.basesyntax.model.ma;

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
        return super.toString() + " is Coach{"
                + "experience=" + experience
                + ", track=" + track
                + '}';
    }
}
