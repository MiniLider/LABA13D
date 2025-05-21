import java.util.LinkedList;//очередь
import java.util.Queue;

public class Zadanie3 {
    private static final int BUFFER_SIZE = 5;//размер буфера
    private static final Queue<Integer> buffer = new LinkedList<>();//очередь
    private static final Object lock = new Object();//доступ к очереди

    public static void main(String[] args) {
        // Производитель
        Thread producer = new Thread(() -> {
            int value = 0;
            while (true) {
                synchronized (lock) {
                    try {
                        while (buffer.size() == BUFFER_SIZE) {
                            System.out.println("Буфер заполнен, производитель ждет...");
                            lock.wait(); //пока потребитель освободит место
                        }
                        buffer.offer(value); //данные в буфер
                        System.out.println("Производитель произвел: " + value);
                        value++;
                        lock.notify(); //увед что есть данные
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        });

        // Потребитель
        Thread consumer = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    try {
                        while (buffer.isEmpty()) {
                            System.out.println("Буфер пуст, потребитель ждет...");
                            lock.wait(); // пока производитель добавит данные
                        }
                        int consumedValue = buffer.poll(); //данные из буфера
                        System.out.println("Потребитель потребил: " + consumedValue);

                        lock.notify(); // увед производителя, что место освободилось
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        });
        producer.start();
        consumer.start();
    }
}