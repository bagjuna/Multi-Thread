package thread.cas.SpinLockBad;

import static thread.util.MyLogger.*;

public class SpinLockMain {
	public static void main(String[] args) {
		// SpinLockBad spinLock = new SpinLockBad();
		SpinLock spinLock = new SpinLock();
		Runnable task = new Runnable() {
			@Override
			public void run() {
				spinLock.lock();
				// critical section
				try {
					log("비즈니스 로직 실행");
				} finally {
					spinLock.unlock();
				}
			}
		};

		Thread t1 = new Thread(task, "thread-1");
		Thread t2 = new Thread(task, "thread-2");

		t1.start();
		t2.start();
	}
}
