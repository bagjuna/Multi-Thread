package thread.cas.increment;

import static thread.util.ThreadUtils.*;

import java.util.ArrayList;

public class IncrementThreadMain {
	public static final int THREAD_COUNT = 1000;

	public static void main(String[] args) throws InterruptedException {
		test(new BasicInteger());
		test(new VolatileInteger());
		test(new SyncInteger());
		test(new MyAtomicInteger());
	}

	private static void test(IncrementInteger incrementInteger) throws InterruptedException {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				sleep(10);
				incrementInteger.increment();
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

		int result = incrementInteger.get();
		System.out.println(incrementInteger.getClass().getSimpleName() + " result: " + result);
	}
}

