package core.basesyntax.model.game.dao;

import core.basesyntax.model.game.Bowman;
import core.basesyntax.model.game.Character;
import core.basesyntax.model.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CharacterDaoImpl implements CharacterDao {
    @Override
    public Character save(Character character) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(character);
            transaction.commit();
            return character;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("There was an error inserting character "
                    + character, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<? extends Character> findAllByPowerAcs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Character> criteriaQuery
                    = criteriaBuilder.createQuery(Character.class);
            Root<Character> root = criteriaQuery.from(Character.class);
            return session.createQuery(criteriaQuery.orderBy(
                    criteriaBuilder.asc(root.get("power"))))
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("There was an error retrieving characters", e);
        }
    }
}
