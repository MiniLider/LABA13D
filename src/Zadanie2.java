public class Zadanie2 {
    public static void main(String[] args) {
        Object lock = new Object();
        //Поток 1
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    System.out.println("Thread-1");
                    try {
                        lock.notify();
                        lock.wait();    //ожид очереди
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        });
        // Поток 2
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    System.out.println("Thread-2");
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}

