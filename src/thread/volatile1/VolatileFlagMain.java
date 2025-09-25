package thread.volatile1;

import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

public class VolatileFlagMain {

	public static void main(String[] args) {
		MyTask task = new MyTask();
		Thread t = new Thread(task, "work");
		log("runFlag - " + task.runFlag);
		t.start();

		sleep(1000);
		log("runFlag를 false로 변경 시도");
		task.runFlag = false; // runFlag를 false로 변경
		log("runFlag - " + task.runFlag);
		log("main 종료");

	}

	static class MyTask implements Runnable {
		// boolean runFlag = true;
		volatile boolean runFlag = true; // volatile 키워드 사용

		@Override
		public void run() {
			log("task 시작");
			while (runFlag) {
				// runFlag가 false로 변하면 탈출
			}
			log("task 종료");
		}
	}
}
