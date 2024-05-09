package core.basesyntax.model.ma;

import jakarta.persistence.Entity;

@Entity
public class Mentor extends Person {
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
