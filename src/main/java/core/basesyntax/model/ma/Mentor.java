package core.basesyntax.model.ma;

import javax.persistence.Entity;

@Entity
public class Mentor extends Person {
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
