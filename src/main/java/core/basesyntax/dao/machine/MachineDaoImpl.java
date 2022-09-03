package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.dao.ObjectDao;
import core.basesyntax.dao.ObjectDaoImpl;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MachineDaoImpl extends AbstractDao implements MachineDao {

    private final ObjectDao<Machine> objectDao = new ObjectDaoImpl<>(sessionFactory);

    public MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Machine save(Machine machine) {
        return objectDao.save(machine);
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Machine> criteriaQuery =
                    criteriaBuilder.createQuery(Machine.class);
            Root<Machine> root = criteriaQuery.from(Machine.class);
            int currentYear = LocalDate.now().getYear();
            Predicate experiencePredicate =
                    criteriaBuilder.lessThan(root.get("year"), currentYear - age);
            criteriaQuery.select(root).where(experiencePredicate);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get machines "
                    + "older than " + age, e);
        }
    }
}
