package core.basesyntax.model.ma;

import javax.persistence.Entity;

@Entity
public class Coach extends Person {
    public enum Track {
        JAVA, FE, UI, QA
    }

    private int experience;
    private Track track;

    public Coach() {
    }

    public Coach(int experience, Track track) {
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
        return "Coach{ id = " + super.getId()
                + ", name = " + super.getName()
                + ", age = " + super.getAge()
                + ", experience = " + experience
                + ", track = '" + track + "' }";
    }
}
