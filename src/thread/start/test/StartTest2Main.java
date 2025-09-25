package thread.start.test;

import static thread.util.MyLogger.log;

public class StartTest2Main {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " main() start");

        Thread thread = new Thread(new CounterThread(),"counter");
        thread.start();

        System.out.println(Thread.currentThread().getName() + " main() end");
    }

    static class CounterThread extends Thread {


        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                log("value: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
