package thread.control.interrupt;

import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

public class ThreadStopMainV1 {
	public static void main(String[] args) {

		MyTask task = new MyTask();
		Thread thread = new Thread(task, "work");
		thread.start();

		sleep(7000); // 4초 대기
		log("작업 중지 요청 runFlag = false");
		task.runFlag = false; // 작업 중지 요청

	}

	static class MyTask implements Runnable {

		volatile boolean runFlag = true;

		@Override
		public void run() {
			while (runFlag) {
				log("작업 중... ");
				sleep(3000); // 3초 대기
			}
			log("자원 정리");
			log("작업 종료");
		}
	}

}
