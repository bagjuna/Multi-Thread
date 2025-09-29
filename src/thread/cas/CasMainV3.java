package thread.cas;

import static thread.util.MyLogger.*;
import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class CasMainV3 {

	private static final int THREAD_COUNT =100;

	public static void main(String[] args) throws InterruptedException {
		AtomicInteger atomicInteger = new AtomicInteger(0);
		System.out.println("start value = " + atomicInteger.get());

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				incrementAndGet(atomicInteger);
			}
		};

		ArrayList<Thread> threads = new ArrayList<>();
		for (int i = 0; i < THREAD_COUNT; i++) {
			Thread thread = new Thread(runnable);
			threads.add(thread);
			thread.start();
		}

		for (Thread thread : threads) {
			thread.join();
		}

		int result = atomicInteger.get();
		System.out.println(atomicInteger.getClass().getSimpleName() +" resultValue: " + result);
	}

	private static int incrementAndGet(AtomicInteger atomicInteger) {
		int getValue;
		boolean result;
		do {
			getValue = atomicInteger.get();
			sleep(100);  // 스레드 동시 실행을 위해 일부러 지연
			// log("getValue = " + getValue);
			result = atomicInteger.compareAndSet(getValue, getValue + 1);
			// log("result = " + result);
		} while (!result);
		log("result = " + result + ", value = " + getValue + 1);
		return getValue + 1;
	}
}
