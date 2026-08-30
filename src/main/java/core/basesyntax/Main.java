package core.basesyntax;

import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        AnimalDao animalDao = new AnimalDaoImpl(sessionFactory);
        Animal dog = new Dog();
        dog.setName("Raf");
        dog.setAge(3);
        animalDao.save(dog);
        System.out.println(animalDao.findByNameFirstLetter('r'));
    }
}
