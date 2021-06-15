package core.basesyntax.model.machine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private int productionYear;
    private String maker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int year) {
        this.productionYear = year;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    @Override
    public String toString() {
        return "Machine{"
                + "id=" + id
                + ", year=" + productionYear
                + ", maker='" + maker + '\''
                + '}';
    }
}
