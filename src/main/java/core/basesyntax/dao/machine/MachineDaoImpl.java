package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    public MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Machine save(Machine machine) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(machine);
        transaction.commit();
        session.close();
        return machine;
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Machine m where m.year < :age ");
        query.setParameter("age", LocalDate.now().getYear() - age);
        List<Machine> resultList = query.getResultList();
        return resultList;
    }
}
