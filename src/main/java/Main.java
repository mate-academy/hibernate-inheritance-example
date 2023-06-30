import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.util.HibernateUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnimalDao animalDao = new AnimalDaoImpl(HibernateUtil.getSessionFactory());

        Animal scotynaAnimal = new Animal();
        scotynaAnimal.setAge(456);
        scotynaAnimal.setName("kkk");
        animalDao.save(scotynaAnimal);

        Cat cat = new Cat();
        cat.setColor("Pink");
        cat.setAge(47);
        cat.setName("ks-ks-ks");
        cat.setNumberOfLives(10);
        animalDao.save(cat);

        List<Animal> o = animalDao.findByNameFirstLetter('k');
        for(Animal animal: o) {
            System.out.println(animal);
        }



    }
}
