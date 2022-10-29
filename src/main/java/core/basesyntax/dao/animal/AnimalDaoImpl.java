package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class AnimalDaoImpl extends AbstractDao<Animal> implements AnimalDao {
    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        return findByNameFirstLetterNotSensitiveToRegister(character);
    }

    private List<Animal> findByNameFirstLetterNotSensitiveToRegister(char ch) {
        try (Session session = sessionFactory.openSession()) {
            String upperCaseWord = "'" + Character.toUpperCase(ch) + "%'";
            String lowerCaseWord = "'" + Character.toLowerCase(ch) + "%'";
            Query<Animal> query = session.createQuery(
                    "from Animal a where a.name like " + upperCaseWord
                            + " or a.name like " + lowerCaseWord, Animal.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can`t get animal by first letter: " + ch, e);
        }
    }
}
