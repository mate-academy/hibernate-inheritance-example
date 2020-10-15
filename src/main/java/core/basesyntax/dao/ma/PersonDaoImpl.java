package core.basesyntax.dao.ma;

import core.basesyntax.dao.GeneralDaoImpl;
import core.basesyntax.model.ma.Person;
import org.hibernate.SessionFactory;

public class PersonDaoImpl extends GeneralDaoImpl<Person> implements PersonDao {
    public PersonDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
