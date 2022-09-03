package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.dao.ObjectDao;
import core.basesyntax.dao.ObjectDaoImpl;
import core.basesyntax.model.ma.Person;
import org.hibernate.SessionFactory;

public class PersonDaoImpl extends AbstractDao implements PersonDao {

    private final ObjectDao<Person> objectDao = new ObjectDaoImpl<>(sessionFactory);

    public PersonDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Person save(Person person) {
        return objectDao.save(person);
    }
}
