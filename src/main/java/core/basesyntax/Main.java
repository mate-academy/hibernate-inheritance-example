package core.basesyntax;

import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.dao.figure.FigureDao;
import core.basesyntax.dao.figure.FigureDaoImpl;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {

        Dog bob = new Dog();
        bob.setName("Bob");
        Dog buddy = new Dog();
        buddy.setName("buddy");
        Cat fluffy = new Cat();
        fluffy.setName("Fluffy");
        Animal bull = new Animal();
        bull.setName("Bull");
        AnimalDao animalDao = new AnimalDaoImpl(HibernateUtil.getSessionFactory());
        animalDao.save(bob);
        animalDao.save(buddy);
        animalDao.save(bull);
        animalDao.save(fluffy);
        System.out.println(animalDao.findByNameFirstLetter('B').size());

        FigureDao<Triangle> figureFigureDao =
                new FigureDaoImpl<>(HibernateUtil.getSessionFactory());
        Triangle triangle = new Triangle();
        triangle.setColor("black");
        figureFigureDao.save(triangle);
        System.out.println(figureFigureDao.findByColor("black", Triangle.class));

    }
}
