package thread.executor;

import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

import java.util.Random;

public class RunnableMain {
	public static void main(String[] args) throws InterruptedException {
		MyRunnable task = new MyRunnable();
		Thread thread = new Thread(task, "Thread-1");
		thread.start();
		thread.join();  //
		int result = task.value;
		log("result value = " + result);
	}

	static class MyRunnable implements Runnable {

		int value;

		@Override
		public void run() {
			log("Runnable 실행");
			sleep(2000);
			value = new Random().nextInt(10);
			log("create value = " + value);
			log("Runnable 종료");
		}
	}
}
