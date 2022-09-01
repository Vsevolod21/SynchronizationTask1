import java.util.Random;

public class SellerCar {
    private final AutoShop autoShop;

    public SellerCar(AutoShop autoShop) {
        this.autoShop = autoShop;
    }

    Random random = new Random();
    int time1 = random.nextInt(2000);
    int time2 = random.nextInt(1000);

    public void receiveCar() {
        for (int i = 0; i < 3; i++) {
            try {
//                System.out.println(Thread.currentThread().getName() + " закручивает последние гайки");
                Thread.sleep(time1);
                synchronized (this) {
                    autoShop.getCars().add(new Car());
                    System.out.println(Thread.currentThread().getName() + " выпустил  авто");
                    notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Car sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (autoShop.getCars().size() == 0) {
                System.out.println("Машин нет!");
                wait();
            }
            Thread.sleep(time2);
            System.out.println("Всего машин в салоне: " + autoShop.getCars().size());
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return autoShop.getCars().remove(0);
    }
}
