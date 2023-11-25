import core.basesyntax.dao.machine.MachineDao;
import core.basesyntax.dao.machine.MachineDaoImpl;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;

public class MachineRunner {
    public static void main(String[] args) {
        List<Runnable> runnableList = new ArrayList<>();
        runnableList.add(getRunnable());
        runnableList.forEach(Runnable::run);
    }

    public static Runnable getRunnable() {
        return () -> {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Car cabrio = new Car();
            cabrio.setHorsePower(150);
            cabrio.setMaker("BMW");
            cabrio.setModel("750");
            cabrio.setYear(2013);
            MachineDao machineDao = new MachineDaoImpl(sessionFactory);
            machineDao.save(cabrio);

            Truck heavy = new Truck();
            heavy.setMaker("CAT");
            heavy.setYear(2013);
            heavy.setColor("Yellow");
            heavy.setMaxAllowedWeight(20);
            machineDao.save(heavy);

            List<Machine> carsList = machineDao.findByAgeOlderThan(5);
            System.out.println("cars in list: " + carsList.size());
            carsList.forEach(System.out::println);
        };
    }
}
