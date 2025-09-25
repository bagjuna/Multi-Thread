
package thread.start.test;

import static thread.util.MyLogger.log;

public class StartTest4Main {
    public static void main(String[] args) {

        Thread a = new PrinterThread("A", 1000);
        Thread b = new PrinterThread("B", 500);
        Thread threadA = new Thread(a,"Thread-A");
        Thread threadB = new Thread(b,"Thread-B");
        threadA.start();
        threadB.start();
    }

    static class PrinterThread extends Thread {
        private final String content;
        private final int sleepMs;

        public PrinterThread(String content, int sleepMs) {
            this.content = content;
            this.sleepMs = sleepMs;
        }

        @Override
        public void run() {
            while (true) {
                log(content);
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
            }
        }
    }

}
