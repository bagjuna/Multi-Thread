package thread.start;

public class DemonThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " main() start");
        DemonThread demonThread = new DemonThread();
//        System.out.println(Thread.currentThread().getName() + " start() 호출 전");
        demonThread.setDaemon(true); // 데몬 스레드로 설정
        demonThread.start();
//        System.out.println(Thread.currentThread().getName() + " start() 호출 후");

        System.out.println(Thread.currentThread().getName() + " main() end");
    }

    static class DemonThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " run()");
            try {
                Thread.sleep(10000); // 10초 실행
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " run() end");
        }


    }
}
