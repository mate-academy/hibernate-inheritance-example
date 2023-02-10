package core.basesyntax.model.machine;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Machine {
    private int year;
    private String maker;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }
}
