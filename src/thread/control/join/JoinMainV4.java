package thread.control.join;

import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

public class JoinMainV4 {

	public static void main(String[] args) throws InterruptedException {
		log("Start");
		SumTask task1 = new SumTask(0, 50);

		Thread thread1 = new Thread(task1, "thread-1");

		thread1.start();

		// 스레드가 종료 될 때까지 기다림
		log("join(10000) - main 스레드가 thread1 가 종료될 때까지 최대 1초 대기");
		thread1.join(1000); // thread1이 종료될 때까지 최대 1초 대기
		log("main 스레드 대기 완료");

		log("task1.result = " + task1.result);

		int sumAll = task1.result;
		log("sumAll = " + sumAll);
		log("End");
	}

	static class SumTask implements Runnable {
		int startValue;
		int endValue;
		int result = 0;

		public SumTask(int startValue, int endValue) {
			this.startValue = startValue;
			this.endValue = endValue;
		}

		@Override
		public void run() {
			log("작업 시작");
			sleep(2000); // 2초 대기
			int sum = 0;
			for (int i = startValue; i <= endValue; i++) {
				sum += i;
			}
			result = sum;
			log("작업 완료: result = " + result);

		}
	}
}
