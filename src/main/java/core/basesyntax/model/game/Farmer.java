package core.basesyntax.model.game;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("farmer")
public class Farmer extends Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double money;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Farmer {"
                + "id=" + id
                + ", money=" + money
                + '}';
    }
}
