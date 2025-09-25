package thread.control.interrupt;

import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

public class ThreadStopMainV2 {
	public static void main(String[] args) {

		MyTask task = new MyTask();
		Thread thread = new Thread(task, "work");
		thread.start();

		sleep(4000); // 4초 대기
		log("작업 중지 요청 thread.interrupt() 호출");
		thread.interrupt(); // 스레드에 인터럽트 요청
		log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
	}

	static class MyTask implements Runnable {


		@Override
		public void run() {
			try {
				while (true) {
					log("작업 중... ");
					Thread.sleep(3000);

				}
			} catch (InterruptedException e) {
				log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
				log("interrupt message = " + e.getMessage());
				log("state= " + Thread.currentThread().getState());
			}
			log("자원 정리");
			log("작업 종료");
		}
	}

}
