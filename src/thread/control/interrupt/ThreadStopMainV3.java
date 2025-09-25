package thread.control.interrupt;

import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

public class ThreadStopMainV3 {
	public static void main(String[] args) {

		MyTask task = new MyTask();
		Thread thread = new Thread(task, "work");
		thread.start();

		sleep(100); // 짧은 시간 대기 (작업이 시작되도록)
		log("작업 중지 요청 thread.interrupt() 호출");
		thread.interrupt(); // 스레드에 인터럽트 요청
		log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
	}

	static class MyTask implements Runnable {

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {  // 인터럽트 상태 변경X
				log("작업 중... ");
			}
			log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
			try {
				log("자원 정리 시도");
				// 자원 정리 작업 (예: 파일 닫기, DB 연결 해제 등)
				Thread.sleep(1000);
				log("자원 정리 완료");
			} catch (InterruptedException e) {
				log("자원 정리 실패 - 자원 정리 중 인터럽트 발생");

				log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted());

			}
			log("작업 종료");
		}

	}
}
