public class ThreadState {

    public static void main(String[] args) throws InterruptedException {

        Thread myThread = new Thread(() -> {
            System.out.println("Поток выполняет свою работу...");
        });
        //ПЕРЕД запуском (NEW)
        System.out.println("Состояние потока ПЕРЕД запуском: " + myThread.getState());

        myThread.start();

        //ПОСЛЕ запуска RUNNABLE -TIMED_WAITING
        System.out.println("Состояние потока ПОСЛЕ запуска: " + myThread.getState());

        Thread.sleep(100); //до

        //(TIMED_WAITING или RUNNABLE)
        System.out.println("Состояние потока ВО ВРЕМЯ выполнения: " + myThread.getState());

        myThread.join(); //ожидание
        System.out.println("Состояние потока ПОСЛЕ завершения: " + myThread.getState()); //TERMINATED
    }
}