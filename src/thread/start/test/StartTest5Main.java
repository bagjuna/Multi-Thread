

package thread.start.test;

import static thread.util.MyLogger.log;

public class StartTest5Main {
    public static void main(String[] args) {

        Runnable a = new PrinterThread("A", 1000);
        Runnable b = new PrinterThread("B", 500);
        Thread threadA = new Thread(a,"Thread-A");
        Thread threadB = new Thread(b,"Thread-B");
        threadA.start();
        threadB.start();
    }

    static class PrinterThread implements Runnable {
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
