package core.basesyntax.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't make a new session factory", e);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
