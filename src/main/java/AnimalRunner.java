import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;

public class AnimalRunner {
    public static void main(String[] args) {
        List<Runnable> runnableList = new ArrayList<>();
        runnableList.add(getRunnable());
        runnableList.forEach(Runnable::run);
    }

    public static Runnable getRunnable() {
        return () -> {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Cat tom = new Cat();
            tom.setAge(3);
            tom.setColor("Black");
            tom.setName("Tomas");
            tom.setNumberOfLives(9);
            AnimalDao animalDao = new AnimalDaoImpl(sessionFactory);
            animalDao.save(tom);

            Dog taker = new Dog();
            taker.setAge(5);
            taker.setName("taker");
            taker.setOwner("Hunter");
            taker.setTailLength(10);
            animalDao.save(taker);

            List<Animal> animalsList = animalDao.findByNameFirstLetter('T');
            System.out.println("animals in list: " + animalsList.size());
            animalsList.forEach(System.out::println);
        };
    }

    ;
}
