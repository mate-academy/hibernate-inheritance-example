package core.basesyntax.dao.machine;

import org.hibernate.SessionFactory;

public class CarDaoImpl extends MachineDaoImpl implements CarDao {
    protected CarDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
