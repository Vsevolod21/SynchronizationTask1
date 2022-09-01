public class Main {
    public static void main(String[] args) throws InterruptedException {

        final AutoShop autoShop = new AutoShop();

        new Thread(null, autoShop::sellCar, "Покупатель 1").start();
        new Thread(null, autoShop::sellCar, "Покупатель 2").start();
        new Thread(null, autoShop::sellCar, "Покупатель 3").start();

        Thread.sleep(1000);
        new Thread(null, autoShop::productCar, "Производитель " +
                "Тойота").start();

    }
}
