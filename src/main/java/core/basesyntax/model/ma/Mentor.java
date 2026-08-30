package core.basesyntax.model.ma;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "mentors")
public class Mentor extends Person {
    private String programmingLanguage;
    private String institute;

    public Mentor() {
    }

    public Mentor(String programmingLanguage, String institute) {
        super();
        this.programmingLanguage = programmingLanguage;
        this.institute = institute;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    @Override
    public String toString() {
        return "Mentor{"
               + "programmingLanguage='" + programmingLanguage + '\''
               + ", institute='" + institute + '\''
               + '}';
    }
}
