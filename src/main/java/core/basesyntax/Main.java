package core.basesyntax;

import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {

        AnimalDao animalDao = new AnimalDaoImpl(HibernateUtil.getSessionFactory());
       Dog animal = new Dog();
       animal.setTailLength(21);
       animal.setName("Dima");
       animalDao.save(animal);
       System.out.println(animalDao.findByNameFirstLetter('D'));

    }
}
