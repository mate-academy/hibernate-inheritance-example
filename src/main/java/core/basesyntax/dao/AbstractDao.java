package core.basesyntax.dao;

import javax.persistence.EntityManagerFactory;

public abstract class AbstractDao {
    protected final EntityManagerFactory entityManagerFactory;

    protected AbstractDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
}
