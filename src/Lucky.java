import java.util.concurrent.atomic.AtomicInteger;

public class Lucky {
    private static AtomicInteger x = new AtomicInteger(0);
    private static AtomicInteger count = new AtomicInteger(0);

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            int temp;
            while ((temp = x.getAndIncrement()) < 999999) {
                if ((temp % 10) + (temp / 10) % 10 + (temp / 100) % 10 == (temp / 1000)
                        % 10 + (temp / 10000) % 10 + (temp / 100000) % 10) {
                    System.out.println(temp);
                    count.getAndIncrement();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}