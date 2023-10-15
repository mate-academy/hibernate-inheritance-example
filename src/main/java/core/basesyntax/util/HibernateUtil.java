package core.basesyntax.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private SessionFactory factory = null;

    public SessionFactory getSessionFactory() {
        if (factory == null) {
            try {
                factory = new Configuration().configure().buildSessionFactory();
            } catch (Exception e) {
                throw new RuntimeException("Error creating SessionFactory", e);
            }
        }
        return factory;
    }
}
