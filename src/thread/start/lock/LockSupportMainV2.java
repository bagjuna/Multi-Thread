package thread.start.lock;

import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class LockSupportMainV2 {
	public static void main(String[] args) {
		Thread thread1 = new Thread(new ParkTest(), "Thread-1");
		thread1.start();

		// 잠시 대기하여 Thread-1이 park 상태에 빠질 시간을 준다
		sleep(100);
		log("Thread-1 state: " + thread1.getState());

		// log("main -> unpark(Thread-1)");
		// LockSupport.unpark(thread1); // 1. unpark 사용
		// thread1.interrupt();  // 2. interrupt 사용



	}


	static class ParkTest implements Runnable {
		@Override
		public void run() {
			log("park 시작");
			LockSupport.parkNanos(2_000_000_000); // 2초 동안 park 상태 유지
			log("park 종료, state: " + Thread.currentThread().getState());
			log("인터럽트 상태: " + Thread.currentThread().isInterrupted());
		}


	}
}
