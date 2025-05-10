package core.basesyntax;

import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.util.HibernateUtil;
import java.util.List;
import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {
        Dog rob = new Dog();
        rob.setName("Rob");
        rob.setTailLength(1);
        Dog uddy = new Dog();
        uddy.setName("Uddy");
        uddy.setTailLength(2);
        Cat fluffy = new Cat();
        fluffy.setName("Fluffy");
        Animal doll = new Animal();
        doll.setName("Doll");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        AnimalDaoImpl animalDao = new AnimalDaoImpl(sessionFactory);
        animalDao.save(rob);
        animalDao.save(uddy);
        animalDao.save(doll);
        animalDao.save(fluffy);

        List<Animal> animalsByNameFirstLetter = animalDao.findByNameFirstLetter('B');
        System.out.println(animalsByNameFirstLetter.size());
        animalsByNameFirstLetter.forEach(System.out::println);
    }
}
