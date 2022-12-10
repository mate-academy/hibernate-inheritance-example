package core.basesyntax.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class HibernateUtil {
    @PersistenceUnit(name = "my-persistence-unit")
    private static EntityManagerFactory factory;

    private HibernateUtil(){
    }

    private static EntityManagerFactory getFactory() {
        return factory;
    }
}
